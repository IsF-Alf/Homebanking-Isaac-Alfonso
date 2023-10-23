const { createApp } = Vue;

createApp({
  data() {
    return {
      clientsInformation: {},
      accounts: [],
      loans: [],
    };
  },
  created() {
    axios
      .get("/api/clients/current")
      .then((response) => {
        client = response.data;
        this.clientsInformation = client;
        console.log(client);
        this.accounts = client.accounts;
        console.log(this.accounts);
        this.loans = client.loans;
        console.log(this.loans);
      })
      .catch((error) => console.log(error));
  },
  methods: {
    logoutClient(){
          axios
          .post("/api/logout")
          .then((response) => {
            console.log("signed out!!!");
            location.href = "http://localhost:8080";
          })
          .catch((error) => console.log(error));
        }
      },
}).mount("#app");
