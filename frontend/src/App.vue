<script>
import BookDataTable from "./components/BookDataTable.vue";
import Header from "./components/Header.vue";


export default {
  name:'App',
  components: {
    BookDataTable,
    Header
  }
}
</script>

<template>
  <div>
    <Header></Header>
    <BookDataTable></BookDataTable>
  </div>
</template>

<style>
@import url('https://fonts.googleapis.com/css2?family=Source+Sans+Pro:ital,wght@0,400;1,700&display=swap');
@import url('https://fonts.googleapis.com/icon?family=Material+Icons');

</style>
