<template>
  <div class="margintable">
    <va-data-table :items="items" :columns="columns" :striped="true" :hoverable="true">
      <template #cell(action)="{ rowIndex: index }">
        <va-button-group flat>
          <va-button icon="create" @click="handleChangeClick(this.items[index])"/>
          <va-button icon="clear" @click="handleDeleteClick(this.items[index])"/>
        </va-button-group>
      </template>
    </va-data-table>
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
      <va-input
          class="mb-4"
          v-model="value"
          label="Name"
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

export default {
  name: 'BookDataTable',
  components: {
    VaDataTable,
    VaButtonGroup,
    VaButton,
    VaModal
  },
  data() {

    // const items = [
    //   {
    //     id: '0',
    //     name: 'xxx',
    //     author: '',
    //     publisher: '',
    //     action: '',
    //     version: ''
    //   },
    //   {
    //     id: '1',
    //     name: 'eee',
    //     author: '',
    //     publisher: '',
    //     action: '',
    //     version: ''
    //   },
    //   {
    //     id: '2',
    //     name: 'sss',
    //     author: '',
    //     publisher: '',
    //     action: '',
    //     version: ''
    //   },
    //   {
    //     id: '3',
    //     name: '444',
    //     author: '',
    //     publisher: '',
    //     action: '',
    //     version: ''
    //   }
    // ]

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
      deleteMessage: "",
      showDeleteConfirm: false,
      currentId: -1,
      currentBook: {},
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
      axios.get("").then(response => {
        if (response.status === 200) {
          this.$vaToast.init("删除成功")
        } else {
          this.$vaToast.init("删除失败")
        }
      })

    },

  }
}
</script>


<style>
.margintable {
  margin-left: 30px;
  margin-right: 30px;
}
</style>