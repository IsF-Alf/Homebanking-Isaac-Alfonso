const { createApp } = Vue;

createApp({
  data() {
    return {
      accounts: [],
      transactions: [],
      account: ``,
    };
  },
  created() {
    let param = location.search;
    let params = new URLSearchParams(param);
    let idClient = params.get("id");    
    axios
      .get(`/api/accounts/${idClient}`)
      .then((accountsAll) => {
        this.accounts = accountsAll.data;
        this.transactions = this.accounts.transactions;
        this.account = this.accounts.number;
        console.log(this.accounts);
        console.log(this.accounts.transactions);
      })
      .catch((err) => console.log(err));
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
  },
}).mount("#app");
