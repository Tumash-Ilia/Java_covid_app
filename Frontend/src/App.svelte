<script>
    import WhoCases from './WhoCases.svelte';
    import WhoVaccination from './WhoVaccination.svelte';
    import MzcrVsWho from './MzcrVsWho.svelte';
    import NetworkAlert from './NetworkAlert.svelte';
    import {onMount} from "svelte";

    let isOnline = navigator.onLine;
    let records_dates = {dates: []}
    let selectedDate

    const hostname = sti_app.env.hostname ? sti_app.env.hostname : "http://localhost:8080"

    async function handleClick() {
        try {
            const response = await fetch(`${hostname}/downloadData`)
            if (response.ok) {
                records_dates = await fetchDates()
                // console.log(records_dates)
            }
        } catch (e) {
            console.error(error)
        }
    }

    async function fetchDates() {
        return fetch(`${hostname}/existedDate`)
            .then(response => response.json())
            .catch(error => {
                console.error(error)
            })
    }

    onMount(async () => {
        await update()
    })

    async function update(){
        records_dates = await fetchDates()
    }

    setInterval(update, 1000 * 60 * 10)
</script>

<svelte:window on:online={() => isOnline = true} on:offline={() => isOnline = false}/>

{#if !isOnline }
    <NetworkAlert/>
{/if}

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid justify-content-end">
        <button type="button" class="btn btn-success" on:click={handleClick}>
            Download data
        </button>
    </div>
</nav>

<main>
    <div class="container py-5">
        <div class="py-5">
            <WhoCases {records_dates}/>
        </div>
        <div class="py-5">
            <WhoVaccination {records_dates}/>
        </div>
        <div class="py-5">
            <MzcrVsWho {records_dates}/>
        </div>
    </div>
</main>