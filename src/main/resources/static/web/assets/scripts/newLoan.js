const { createApp } = Vue;

createApp({
  data() {
    return {
      loans: [],
      loanName: "",
      maxAmount: 0,
      payments: [],
      interestPercentage: 0,
    };
  },
  created() {
    axios
      .get("/api/clients/current")
      .then((response) => {
        client = response.data;
        console.log(client);
      })
      .catch((err) => console.log(err));
  },
  methods: {
    createNewLoan() {
      const newLoan = `name=${this.loanName}&maxAmount=${this.maxAmount}&payments=${this.payments}&interestPercentage=${this.interestPercentage}`;
      axios
        .post("/api/admin/loans", newLoan)
        .then((result) => {
          location.pathname = `/web/accounts.htmll`;
        })
        .catch((error) => {
          console.log(error);
        });
    },

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
