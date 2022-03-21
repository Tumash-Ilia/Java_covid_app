<script>
    import Chart from "chart.js/auto"
    import {tick} from "svelte";

    const hostname = sti_app.env.hostname ? sti_app.env.hostname : "http://localhost:8080"

    export let records_dates
    let context
    let chart
    let data = {}
    let dates = []
    let last_date

    let records_cases = {}
    let records_vac = {}

    let who_24_h = []
    let mzcr_24_h = []

    let who_total_cases
    let mzcr_total_cases

    let who_total_vaccination = []
    let mzcr_total_vaccination = []

    $: if (records_dates.dates.length > 0) {
        updating(records_dates)
    }

    async function updating(records_dates){
        last_date = await fetchDate(records_dates)
        records_cases = await fetchDataWho()
        records_vac = await fetchDataVac()

        showChart()

        who_total_cases = Object.entries(records_cases).filter(item => item[1].date === last_date)[0][1].who_total
        mzcr_total_cases = Object.entries(records_cases).filter(item => item[1].date === last_date)[0][1].mzcr_total

        who_total_vaccination = [
            Object.entries(records_vac).filter(item => item[1].file_date === last_date)[0][1].who_total_vac,
            Object.entries(records_vac).filter(item => item[1].file_date === last_date)[0][1].who_last_update_date,
        ]

        mzcr_total_vaccination = [
            Object.entries(records_vac).filter(item => item[1].file_date === last_date)[0][1].mzcr_total_vac,
            Object.entries(records_vac).filter(item => item[1].file_date === last_date)[0][1].mzcr_last_update_date,
        ]
    }

    const prepareChartData = () => {
        who_24_h = []
        mzcr_24_h = []

        Object.entries(records_cases).forEach(item => {
            who_24_h.push(+item[1].who_24_hours)
            mzcr_24_h.push(+item[1].mzcr_24_hours)
        })
    }

    const showChart = () => {
        prepareChartData()
        !chart ? buildChart(who_24_h, mzcr_24_h) : updateChart(who_24_h, mzcr_24_h)
    }

    const buildChart = (who, mzcr) => {
        chart = new Chart(context, {
            data: {
                datasets: [{
                    type: 'line',
                    label: 'WHO',
                    data: who,//who_24_h,
                    backgroundColor: 'rgba(255, 99, 132, 1)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                }, {
                    type: 'line',
                    label: 'MZCR',
                    data: mzcr,//mzcr_24_h,
                    backgroundColor: 'rgba(54, 162, 235, 1)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                }],
                labels: Object.keys(records_cases)
            },
        })
    }

    const updateChart = (who, mzcr) => {
        chart.data.labels = Object.keys(records_cases)
        chart.data.datasets[0].data = who
        chart.data.datasets[1].data = mzcr
        chart.update()
    }



    async function fetchDate(records_dates) {
        const last_date = records_dates.dates[records_dates.dates.length - 1]
        return last_date;
    }

    async function fetchDataVac() {
        const response = await fetch(`${hostname}/mzcrVsWhoVac`);
        const records = await response.json();

        if (response.ok) {
            return records;
        } else {
            throw new Error('error');
        }
    }

    async function fetchDataWho() {
        const response = await fetch(`${hostname}/mzcrVsWhoCases`);
        const records = await response.json();

        if (response.ok) {
            return records;
        } else {
            throw new Error('error');
        }
    }

    const formatNumber = (value) => {
        return new Intl.NumberFormat('cs-CZ').format(+value)
    }
</script>

<div class="py-5">
    <h4>WHO vs MZCR cases and vaccination in Czechia by {last_date}</h4>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Source page</th>
            <th scope="col">Vaccinated total</th>
            <th scope="col">Cases total</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>WHO</td>
            <td>{formatNumber(who_total_vaccination[0])} (last update: {who_total_vaccination[1]})</td>
            <td>{formatNumber(who_total_cases)}</td>
        </tr>
        <tr>
            <td>MZCR</td>
            <td>{formatNumber(mzcr_total_vaccination[0])} (last update: {mzcr_total_vaccination[1]})</td>
            <td>{formatNumber(mzcr_total_cases)}</td>
        </tr>
        </tbody>
    </table>
</div>

<div class="py-5">
    <h4>WHO vs MZCR 24 hours cases </h4>
    <canvas bind:this={context} height="100"></canvas>
</div>


