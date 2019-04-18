<template>
	<ul>
		<li v-for="project in projects">
			<SingleProjectView :projectData="project"/>
		</li>

	</ul>
</template>

<script>
	import SingleProjectView from '@/components/SingleProjectView.vue'
	export default {
		name:"Projects",
		data() {
			return{
				projects : null
			}
		},
		components: {
			SingleProjectView
		},
		props:{
			SingleProject : Object
		},
		methods:{

		},
		mounted(){
			const axios = require('axios');
			axios.get('http://localhost:8080/projects/')
			.then(response =>{
				this.projects = response.data;
			});
		}

	};
</script>