<script>
    export let countries
    export let selected
    export let disabled = false
</script>

<select bind:value={selected} class="form-select" {disabled} on:change>
    {#each countries as country}
        <option value="{country}">{country}</option>
    {/each}
</select>
