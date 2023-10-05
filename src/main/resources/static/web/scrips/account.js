const { createApp } = Vue;

createApp({
  data() {
    return {
      firstName: "",
      lastName: "",
      email: "",
      clientsInformation: {},
      accounts:[],
    };
  },
  created() {
    axios
      .get("/api/clients/2")
      .then((response) => {
        client = response.data;
        this.clientsInformation = client;
        console.log(client)
        this.accounts=client.accounts
      })
      .catch((error) => console.log(error));
  },
  methods: {
    addUser() {
      const clientsData = {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
      };
      axios
        .post("/api/clients", clientsData)
        .then((response) => {
          alert("Usuario creado");
          console.log(response);
        })
        .catch((error) => console.log(error));
    },
  },
}).mount("#app");