<template>
  <div>
    <h2>유튜브 전용 페이지</h2>
    <youtube-search @search-input="search"></youtube-search>
    <youtube-search-result
        :videos="videos"
    ></youtube-search-result>
  </div>
</template>

<script>
import YoutubeSearch from "@/components/youtube/YoutubeSearch.vue";
import YoutubeSearchResult from "@/components/youtube/YoutubeSearchResult.vue";
import axios from 'axios'
export default {
    name: "YoutubeView",
    components:{
        YoutubeSearch,
        YoutubeSearchResult,
    },
    data(){
        return {
            videos: [],
        };
    },
    methods:{
        search(value){
            console.log(value);
            const URL = "https://www.googleapis.com/youtube/v3/search";
            const API_KEY = process.env.VUE_APP_YOUTUBE_API_KEY;

            axios({
                url:URL,
                method:"GET",
                params:{
                    key: API_KEY,
                    part: "snippet",
                    q:value,
                    type:"video",
                    maxResults:10,
                },
            })
            .then((res)=>{
                console.log(res);
                return res.data.items;
            })
            .then((res)=>{
                console.log(res);
                this.videos = res;
            })
            .catch((err)=>{console.log(err)})
        }
    }
}
</script>

<style scoped>
    h2{
        color:red
    }
</style>