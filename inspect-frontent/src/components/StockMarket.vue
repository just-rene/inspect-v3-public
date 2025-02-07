<template>
    <v-app>


        <v-container class=" d-flex justify-center">
            <v-card class="w-75">
                <v-autocomplete clearable label="subscribe" v-model="selectedStockMarketTicker"
                    :items=stockMarketTicker></v-autocomplete>
                <div v-if="warning"> <v-alert :text=warning variant="tonal" type="error"></v-alert></div>
                <div v-if="success"> <v-alert :text=success variant="tonal" type="success"></v-alert></div>
                <v-btn class="mt-2" block type="submit" @click="sendStockMarketSubscription">Submit</v-btn>
            </v-card>
        </v-container>

        <v-container class=" flex-wrap d-flex justify-center">
            <v-card v-for="t in tickerSubs " v-bind:key="tickerSubs.ticker" class="w-25 ma-4 pa-4">

                <v-card-item>
                    <v-card-title>
                        {{ t.ticker }}
                    </v-card-title>

                    <v-card-subtitle>
                        {{ t.correlationType.replace("_", " ") }}
                    </v-card-subtitle>
                </v-card-item>

                <v-card-text>
                    <div>Positive Correlation: {{ t.correlationPositive }}</div>
                    <div>Negative Correlation: {{ t.correlationNegative }}</div>
                    <div>Neutral Correlation:{{ t.correlationNeutral }}</div>
                    <div>Data Points: {{ t.dataPoints }}</div>
                </v-card-text>
       
            </v-card>
        </v-container>
    </v-app>
</template>
<script>


export default {
    data() {
        return {
            tickerSubs: [],
            selectedStockMarketTicker: "",
            stockMarketTicker: [],
            host: process.env.VUE_APP_BASE_URL,
            warning: "",
            success: "",
            eventSource : {},
        }
    },
    computed: {


    },

    mounted: function () {
        this.getStockMarketTicker();
        this.getTickerSubs();
    },
    created(){
        this.eventSource = new EventSource(this.host + "/ticker-stream");
        this.eventSource.addEventListener("message", this.handleMessage);
    },
    methods: {
        handleMessage(event) {
        console.log(event)
      //this.messages.push(event.data);
    },
        //TODO: validation of response not correct
        async sendStockMarketSubscription() {
            if (this.selectedStockMarketTicker === "") {
                this.success = "";
                this.warning = "no ticker selected";
                setTimeout(() => {
                    this.warning = "";
                }, "5000");
                return;
            }
            let requestOptions = {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ ticker: this.selectedStockMarketTicker }),
            };

            let res = await fetch(this.host + "/api/stock-market/create-subscription", requestOptions)

            if (res) {
                this.warning = "";
                this.success = "successfuly subscribed";
                setTimeout(() => {
                    this.success = "";
                }, "2000");
            }


        },

        async getTickerSubs() {
            let res = await fetch(this.host + "/api/stock-market/ticker-subscriptions");
            this.tickerSubs = (await (res.json()));
        },

        async getStockMarketTicker() {
            let res = await fetch(this.host + "/api/stock-market/ticker");
            this.stockMarketTicker = (await (res.json()));
        },
    }

}




</script>