const MEMSOURCE_PROJECT_ENDPOINT = '/api/project';

var app = new Vue({
    el: '#projectApp',
    data() {
        return {
            projects : []
        }
    },
    mounted() {
        this.fetchProjects();
    },
    methods: {
        fetchProjects() {
            return fetch(MEMSOURCE_PROJECT_ENDPOINT)
                .then(response => {
                    return response.json();

                })
                .then(responseJson => {
                    this.projects = responseJson.projects;
                    return responseJson
                });
        }
    }
});