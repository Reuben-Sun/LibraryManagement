<template>
  <div>
    <div class="margin">
      <div class="header_bar">
        <va-popover
            placement="right"
            message="孙政，张文迪，陈涵"
            class="member_hover"
        >
          <va-button>小组成员</va-button>
        </va-popover>
        <div class="search_container">
          <va-input
              id ="nameinput"
              class="input"
              v-model="value"
              label="Name"
              placeholder="Label"
          />
          <va-button class="button" icon="search" @click="searchByName()"/>
        </div>
      </div>
    </div>

    <hr>
    <h1 class="header">图书管理系统</h1>
    <br>
  </div>
</template>

<script>
import {VaButton} from "vuestic-ui";
import {VaPopover} from "vuestic-ui";
import {VaInput} from "vuestic-ui";
import axios from "axios";

export default {
  name: "Header",
  data() {
    return {
      value: '',
      items: [],
    }
  },
  methods: {

    // async refresh() {
    //   const response = await axios.get("/api/bookList")
    //   this.items = response.data
    // },
    searchByName() {
      var name = this.value
      const aaaaa =  axios.get("/api/bookListByName", {
        params: {
          name: this.name
        }
      }).then(response => {
        if (response.status === 200) {
          this.items = aaaaa.data
          this.$vaToast.init("删除成功")
        } else {
          this.$vaToast.init("删除失败")
        }
      }).catch(err => {
        this.$vaToast.init("删除失败")
      })
    }
  },
  components: {
    VaButton,
    VaPopover,
    VaInput
  }
}
</script>

<style lang="less">
.header {
  text-align: center;
  font-size: 40px;
}

.margin {
  margin-top: 10px;
  margin-left: 10px;
}

.header_bar {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;

  .member_hover {
    flex: 0 0 auto;
  }

  .search_container {
    flex: 1 1 auto;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;

    .input {
      flex: 0 0 auto;
      width: 40vw;
    }

    .button {
      flex: 0 0 auto;
    }
  }



}


</style>