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
      .get("/api/clients/2")
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
}).mount("#app");
