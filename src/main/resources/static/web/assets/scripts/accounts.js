const { createApp } = Vue;

createApp({
  data() {
    return {
      clientsInformation: {},
      accounts: [],
      loans: [],
      accountType: null,
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
    logoutClient() {
      axios
        .post("/api/logout")
        .then((response) => {
          console.log("signed out!!!");
          location.href = "http://localhost:8080";
        })
        .catch((error) => console.log(error));
    },
    createAccount() {
      axios
        .post(
          `/api/clients/current/accounts`,
          `accountType=${this.accountType}`
        )
        .then((response) => {
          console.log("created");
          location.reload();
        })
        .catch((error) => console.log("error"));
    },
    deleteAccount(id) {
      axios
        .put(`/api/clients/current/accounts`, `id=${id}`)
        .then(() => {
          location.pathname = `/web/accounts.html`;
        })
        .catch((error) => {
          alert(error.response.data);
        });
    },
  },
}).mount("#app");
