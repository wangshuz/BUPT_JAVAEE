<template>
    <div class="search">
          <span>
            <i class="el-icon-search" style="font-size: 30px;"></i>
          </span>
          <span>
            <input type="text" class="inline-input" @keyup.enter='childValueChange'/>
          </span>
    </div>

</template>

<script>
    export default {
    components: {},
    props: {
      childContent:{
        type:String,
        default:""
      }

    },
    data() {
      return {
       
      };
    },
    methods:{
      childValueChange(event){
        this.$emit("update:childContent",event.currentTarget.value);
      }
    }
  }


</script>

<style scoped>
.search{
  position: absolute;
  overflow: hidden;
  border-radius: 5px;
  left:40%;
  top: 3%;
  z-index: 120;
}

.inline-input{
  border-radius: 5px;
  transform: translateY(-5px);
  height: 30px;
  width:200px;
  caret-color: #333;
}

</style>
