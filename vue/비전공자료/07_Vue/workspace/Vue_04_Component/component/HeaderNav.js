export default {
  template: `
    <header>
      <nav class="container">
        <div>
          <a class="header_nav_home" href="index.html">홈으로</a>
        </div>
        <div class="header_nav_search">
          <form action="#">
            <input type="text" placeholder="검색어를 입력해주세요" />
            <button class="button">검색</button>
          </form>
        </div>
        <div class="header_nav_menuitem">
          <a href="login.html" v-if="getUser">로그인</a>
          <a href="#" @click="logout" v-else>로그아웃</a>
          <a href="#">마이페이지</a>
        </div>
      </nav>
  </header>
    `,
  methods: {
    logout() {
      localStorage.removeItem("loginUser");
      alert("로그아웃 했습니다.");
    },
  },
  computed: {
    getUser() {
      let user = JSON.parse(localStorage.getItem("loginUser"));
      if (!user) {
        return true;
      } else {
        return false;
      }
    },
  },
};
