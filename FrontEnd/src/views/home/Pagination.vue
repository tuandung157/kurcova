<template>
	<div class="overflow-auto">
		<b-pagination
      		v-model="currentPage"
      		:total-rows="rows"
      		:per-page="perPage"
      		aria-controls="my-table"
      	></b-pagination>
	</div>

</template>

<script>
	export default {
		data() {
			perPage: null,
			crrentPage: null,
			items: 
		},
		computed: {

		}
	}
</script>