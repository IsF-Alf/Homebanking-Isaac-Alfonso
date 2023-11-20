const { createApp } = Vue;

createApp({
  data() {
    return {
      clientsInformation: {},
      accounts: [],
      loans: [],
      accountType: null,
      userEmail:"",
    };
  },
  created() {
    axios
      .get("/api/clients/current")
      .then((response) => {
        console.log(response)
        client = response.data;
        this.clientsInformation = client;
        console.log(client);
        this.accounts = client.accounts;
        this.accounts.sort((a, b) => a.id - b.id);
        console.log(this.accounts);
        this.loans = client.loans;
        console.log(this.loans);
        this.userEmail = client.email;
      })
      .catch((error) => console.log(error));
  },
  methods: {
    logoutClient() {
      axios
        .post("/api/logout")
        .then((response) => {
          console.log("signed out!!!");
          location.pathname = `/index.html`;
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
        .patch(`/api/clients/current/accounts`, `id=${id}`)
        .then(() => {
          location.pathname = `/web/accounts.html`;
        })
        .catch((error) => {
          alert(error.response.data);
        });
    },
  },
}).mount("#app");
