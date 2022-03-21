<script>
    import Chart from "chart.js/auto";
    import CountrySelector from "./CountrySelector.svelte"

    const hostname = sti_app.env.hostname ? sti_app.env.hostname : "http://localhost:8080"

    export let records_dates
    let selectedDate
    let context
    let chart
    let data = []
    let countries = []
    let records = []
    let selectedCountries = [
        "Czechia", "Poland", "Germany", "Slovakia", "Austria"
    ]

    const showChart = async (date) => {
        try {
            records = await fetchData(date)
            // console.log(records)
            countries = getCountries()
            prepareChartData()
            !chart ? buildChart(selectedCountries, data) : updateChart(selectedCountries, data)
        } catch (e) {
            console.log(e)
        }
    }

    const buildChart = (labels, data) => {
        chart = new Chart(context, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Confirmed cases last 24 hours',
                    data: data,
                    backgroundColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                    ],
                }]
            },
        })
    }

    const updateChart = (labels, data) => {
        chart.data.labels = labels
        chart.data.datasets[0].data = data
        chart.update()
    }

    const getCountries = () => {
        let items = []

        Object.entries(records).forEach(item => {
            items.push(item[1].country)
        })

        return items
    }

    const prepareChartData = () => {
        data = []
        selectedCountries.forEach(country => {
            data = [...data, Object.entries(records).filter(item => item[1].country === country)[0][1].confirmed_cases_yesterday]
        })
    }

    $: if (records_dates.dates.length > 0 && !selectedDate) {
        selectedDate = records_dates.dates[records_dates.dates.length - 1]
    }

    const setCountries = () => {
        Object.entries(records).forEach(item => {
            countries.push(item[1].country)
        })

        countries = [...countries]
    }

    async function fetchData(date) {
        return fetch(`${hostname}/whoCases?date=${date}`)
            .then(response => response.json())
    }

    const handleSelectedCountriesChange = (event) => {
        prepareChartData()
        updateChart(selectedCountries, data)
    }

    $: if (selectedDate) {
        showChart(selectedDate)
    }
</script>

<h4>WHO Confirmed cases last 24 hours</h4>
<div class="col">
    <select class="form-select my-4" bind:value={selectedDate}>
        {#each records_dates.dates as day}
            <option value="{day}">{day}</option>
        {/each}
    </select>
</div>

<div class="row py-4">
    <div class="col">
        <CountrySelector {countries} bind:selected={selectedCountries[1]} on:change={handleSelectedCountriesChange}/>
    </div>
    <div class="col">
        <CountrySelector {countries} bind:selected={selectedCountries[2]} on:change={handleSelectedCountriesChange}/>
    </div>
    <div class="col">
        <CountrySelector {countries} bind:selected={selectedCountries[3]} on:change={handleSelectedCountriesChange}/>
    </div>
    <div class="col">
        <CountrySelector {countries} bind:selected={selectedCountries[4]} on:change={handleSelectedCountriesChange}/>
    </div>
</div>

<canvas bind:this={context} height="100"></canvas>

