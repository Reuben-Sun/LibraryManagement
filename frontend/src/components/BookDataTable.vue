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
  <div class="margintable">
    <va-data-table :items="items" :columns="columns" :striped="true" :hoverable="true">
      <template #cell(action)="{ rowIndex: index }">
        <va-button-group flat>
          <va-button icon="create" @click="handleChangeClick(this.items[index])"/>
          <va-button icon="clear" @click="handleDeleteClick(this.items[index])"/>
        </va-button-group>
      </template>
    </va-data-table>
    <va-button  icon="add" @click="handleAddClick()"/>
    <va-modal ref="addConfirm" stateful title="添加书籍"
              @cancel="cancelAdd"  @ok="addBook">
      <va-input
          class="mb-4"
          v-model="this.newbookname"
          label="Name"
          placeholder="Label"
      />
      <va-input
          class="mb-4"
          v-model="this.newbookauthor"
          label="Author"
          placeholder="Label"
      />
      <va-input
          class="mb-4"
          v-model="this.newbookpublisher"
          label="Publisher"
          placeholder="Label"
      />
      <va-input
          class="mb-4"
          v-model="this.newbookversion"
          label="Version"
          placeholder="Label"
      />
    </va-modal>

    <va-modal ref="deleteConfirm" stateful title="确认"
              @cancel="cancelDelete" :message="deleteMessage" @ok="deleteBook">
    </va-modal>

    <va-modal ref="changeConfirm" stateful title="更改信息"
              @cancel="cancelChange" @ok="changeBook">
      <va-input
          class="mb-4"
          v-model="this.currentBook.id"
          label="ID"
          placeholder="Label"
          readonly="true"
      />
      <va-input
          class="mb-4"
          v-model="this.currentBook.name"
          label="Name"
          placeholder="Label"
      />
      <va-input
          class="mb-4"
          v-model="this.currentBook.author"
          label="Author"
          placeholder="Label"
      />
      <va-input
          class="mb-4"
          v-model="this.currentBook.publisher"
          label="Publisher"
          placeholder="Label"
      />
      <va-input
          class="mb-4"
          v-model="this.currentBook.version"
          label="Version"
          placeholder="Label"
      />
    </va-modal>


  </div>
</template>

<script>
import {VaButtonGroup} from "vuestic-ui";
import {VaDataTable} from "vuestic-ui";
import {VaButton} from "vuestic-ui";
import {VaModal} from "vuestic-ui";
import axios from "axios";
import {VaPopover} from "vuestic-ui";
import {VaInput} from "vuestic-ui";

export default {
  name: 'BookDataTable',
  components: {
    VaDataTable,
    VaButtonGroup,
    VaButton,
    VaModal,
    VaPopover,
    VaInput
  },
  data() {

    const columns = [
      {key: 'id', verticalAlign: "center"},
      {key: 'name', verticalAlign: "center"},
      {key: 'author', verticalAlign: "center"},
      // {key: 'isbn', verticalAlign: "center"},
      {key: 'publisher', verticalAlign: "center"},
      {key: 'version', verticalAlign: "center"},
      {key: 'action', label: "Action", verticalAlign: "center"},
    ]


    return {
      items: [],
      columns,
      value: '',
      deleteMessage: "",
      showDeleteConfirm: false,
      currentId: -1,
      currentBook: {},
      newbookname:'',
      newbookauthor:'',
      newbookversion:'',
      newbookpublisher:''
    }
  },
  mounted() {
    this.getinfo();
  },
  methods: {
    async getinfo() {
      const response = await axios.get("/api/bookList")
      this.items = response.data
    },

    handleDeleteClick(book) {
      this.$refs.deleteConfirm.show();
      this.deleteMessage = `是否确认删除 ${book.name}`
      this.currentId = book.id;
    },
    handleChangeClick(book) {
      this.$refs.changeConfirm.show();
      this.currentId = book.id;
      this.currentBook = book;
    },
    handleAddClick() {
      this.$refs.addConfirm.show();
    },

    cancelAdd() {

    },

    cancelDelete() {
      this.currentId = -1;
      this.deleteMessage = "";
    },
    cancelChange() {
      this.currentId = -1;
    },
    deleteBook() {
      if (this.currentId < 0) {
        return;
      }
      axios.get("/api/deleteBook", {
        params: {
          id: this.currentId
        }
      }).then(response => {
        if (response.status === 200) {
          this.getinfo();
          this.$vaToast.init("删除成功")
        } else {
          this.$vaToast.init("删除失败")
        }
      }).catch(err => {
        this.$vaToast.init("删除失败")
      })

    },

    changeBook() {
      if (this.currentId < 0) {
        return;
      }
      let id= this.currentId;
      let name=this.currentBook.name;
      let author=this.currentBook.author;
      let version=this.currentBook.version;
      let publisher=this.currentBook.publisher;

      axios.get("/api/updateBook", {
        params: {
          id: id,
          name:name,
          author:author,
          publisher:publisher,
          version:version
        }
      }).then(response => {
        if (response.status === 200) {
          this.getinfo();
          this.$vaToast.init("修改成功")
        } else {
          this.$vaToast.init("修改失败")
        }
      }).catch(err => {
        this.$vaToast.init("修改失败")
      })
    },

    addBook() {
      let name=this.newbookname;
      let author=this.newbookauthor;
      let version=this.newbookversion;
      let publisher=this.newbookpublisher;
      axios.get("/api/newBook", {
        params: {
          name:name,
          author:author,
          publisher:publisher,
          version:version
        }
      }).then(response => {
        if (response.status === 200) {
          this.getinfo();
          this.$vaToast.init("添加成功")
        } else {
          this.$vaToast.init("添加失败")
        }
      }).catch(err => {
        this.$vaToast.init("添加失败")
      })
    },

    searchByName(){
      let name = this.value;

        axios.get("/api/bookListByName", {
        params: {
          name: name
        }
      }).then(response => {
          if (response.status === 200) {
            console.log(name)
            this.items = response.data
            console.log(response.data)
            this.$vaToast.init("修改成功")
          } else {
            this.$vaToast.init("修改失败")
          }
        }).catch(err => {
          this.$vaToast.init("修改失败")
        })
    }

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
.margintable {
  margin-left: 30px;
  margin-right: 30px;
}
</style>