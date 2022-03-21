<script>
    import CountrySelector from "./CountrySelector.svelte"

    const hostname = sti_app.env.hostname ? sti_app.env.hostname : "http://localhost:8080"

    export let records_dates
    let data = []
    let countries = []
    let records = []
    let selectedCountries = [
        "Czechia", "Poland", "Germany", "Slovakia", "Austria"
    ]

    $: if (countries.length) {
        data = []
        selectedCountries.forEach(country => {
            data = [...data, Object.entries(records).filter(item => item[1].country === country)[0][1]]
        })
    }

    $: if (records_dates.dates.length > 0) {
        updating(records_dates)
    }

    async function updating(records_dates) {
        records = await fetchData(records_dates)
        getCountries()
    }

    function getCountries() {
        Object.entries(records).forEach(item => {
            countries.push(item[1].country)
        })

        countries = [...countries]
    }

    const formatNumber = (value) => {
        return new Intl.NumberFormat('cs-CZ').format(+value)
    }

    async function fetchData(records_dates) {
        const last_date = records_dates.dates[records_dates.dates.length - 1]
        const response = await fetch(`${hostname}/whoVac?date=${last_date}`);
        const records = await response.json();

        if (response.ok) {
            return records;
        } else {
            throw new Error('error');
        }
    }
</script>

<h4>WHO Vaccination</h4>

<div class="row py-4">
    <div class="col">
        <CountrySelector {countries} bind:selected={selectedCountries[1]}/>
    </div>
    <div class="col">
        <CountrySelector {countries} bind:selected={selectedCountries[2]}/>
    </div>
    <div class="col">
        <CountrySelector {countries} bind:selected={selectedCountries[3]}/>
    </div>
    <div class="col">
        <CountrySelector {countries} bind:selected={selectedCountries[4]}/>
    </div>
</div>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Country</th>
        <th scope="col">Last updated</th>
        <th scope="col">Vaccinated per 100</th>
        <th scope="col">Vaccinated total</th>
    </tr>
    </thead>
    <tbody>
    {#each data as item, i}
        <tr>
            <td>{selectedCountries[i]}</td>
            <td>{item.date_updated}</td>
            <td>{item.vaccination_per_100}</td>
            <td>{formatNumber(item.vaccination_total)}</td>
        </tr>
    {/each}
    </tbody>
</table>
