Vue.component('landing', {
    template: `
      <div>
        <div class="row">
          <div class="col-sm-12">
            <div class="d-flex align-items-center">
              <search v-on:find-by-id-event="getPortfolio( $event )"></search>
              <div v-if="loading" class="spinner-border ml-auto" role="status"
                   aria-hidden="true"></div>
            </div>
          </div>

          <div class="col-sm-12" v-if="error">
            <div v-if="error" class="alert alert-danger" role="alert">
              {{ error }}
            </div>
          </div>

          <div class="row">
            <div class="col-sm-4">
              <photo v-bind:image="portfolio.photo"></photo>
              <timeline v-bind:portfolio="portfolio"></timeline>
            </div>
            <div class="col-sm-8">
              <card v-bind:portfolio="portfolio"
                              v-bind:key="portfolio.id"></card>
            </div>
          </div>
          
          <update v-bind:portfolio="portfolio"></update> 
        </div>
      </div>
    `,
    data: () => ({
        portfolio: {photo: "/images/user-empty.png"},
        loading: false,
        error: null
    }),
    watch: {
        '$route': 'getPortfolio'
    },
    methods: {
        async getPortfolio(id) {
            this.error = null;
            this.loading = true;
            let response = await fetch(`/portfolio/${id}`);
            if (response.status === 200) {
                this.portfolio = await response.json();
            } else {
                this.portfolio = {photo: "/images/user-empty.png"};
                this.error = response.status === 404 ? 'Portfolio not found!'
                    : 'Error to process the search.';
            }

            this.loading = false;
        }
    }
})
;
