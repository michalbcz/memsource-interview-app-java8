const MEMSOURCE_CONFIGURATION_ENDPOINT = '/api/configuration';

var app = new Vue({
    el: '#configurationApp',
    data() {
        return {
            configuration : {
                username: "",
                password: "",
            },
            showSaved: false,
        }
    },
    mounted() {
        this.fetchProjects();
    },
    methods: {
        fetchProjects() {
            return fetch(MEMSOURCE_CONFIGURATION_ENDPOINT)
                .then(response => {
                    return response.json();

                })
                .then(responseJson => {
                    app.configuration.username = responseJson.username;
                    app.configuration.password = responseJson.password;
                    return responseJson
                });
        },
        saveConfiguration(event) {
            event.preventDefault();
            fetch(MEMSOURCE_CONFIGURATION_ENDPOINT, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    // 'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: JSON.stringify(this.configuration),
            }).then(res => res.json())
                .then(response => console.log('Success:', JSON.stringify(response)))
                .catch(error => console.error('Error:', error));;
            this.showSaved = true;
            setTimeout(() => this.showSaved = false, 5000 /* ms before auto dismiss of saved alert */)
        },
        dismissSavedMessage() {
            this.showSaved = false;
        }
    }
});