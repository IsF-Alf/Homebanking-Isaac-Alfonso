const { createApp } = Vue;

createApp({
  data() {
    return {
      clientsInformation: {},
      accounts: [],
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
      })
      .catch((error) => console.log(error));
  },
}).mount("#app");
