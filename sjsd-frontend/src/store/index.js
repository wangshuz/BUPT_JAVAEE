// src/store/index.js
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    merchant_id: null,
    cart: []
  },
  mutations: {
    setMerchantID(state, id){
      state.merchant_id = id;
    },
    addToCart(state, item) {
      const found = state.cart.find(product => product.id === item.id);
      if (found) {
        found.quantity++;
      } else {
        state.cart.push({ ...item, quantity: 1 });
      }
    },
    removeFromCart(state, item) {
      const index = state.cart.findIndex(product => product.id === item.id);
      if (index !== -1) {
        if (state.cart[index].quantity > 1) {
          state.cart[index].quantity--;
        } else {
          state.cart.splice(index, 1);
        }
      }
    },
    clearCart(state) {
      state.cart = [];
    }
  },
  getters: {
    cartItems(state) {
      return state.cart;
    },
    cartTotal(state) {
      return state.cart.reduce((total, item) => total + item.price * item.quantity, 0).toFixed(2);
    },
    cartCount(state) {
      return state.cart.reduce((total, item) => total + item.quantity, 0);
    }
  }
});
// src/store/index.js
// import Vue from 'vue';
// import Vuex from 'vuex';

// Vue.use(Vuex);

// export default new Vuex.Store({
//   state: {
//     carts: {}  // 用于存储多个商店的购物车信息，以商店ID为key
//   },
//   mutations: {
//     setMerchantID(state, { merchantId, id }) {
//       // 初始化商店ID对应的购物车
//       if (!state.carts[merchantId]) {
//         Vue.set(state.carts, merchantId, []);
//       }
//       state.carts[merchantId].merchant_id = id;
//     },
//     addToCart(state, { merchantId, item }) {
//       // 确保该商店的购物车已经初始化
//       if (!state.carts[merchantId]) {
//         Vue.set(state.carts, merchantId, []);
//       }

//       const cart = state.carts[merchantId];
//       const found = cart.find(product => product.id === item.id);

//       if (found) {
//         found.quantity++;
//       } else {
//         cart.push({ ...item, quantity: 1 });
//       }
//     },
//     removeFromCart(state, { merchantId, item }) {
//       if (!state.carts[merchantId]) return;

//       const cart = state.carts[merchantId];
//       const index = cart.findIndex(product => product.id === item.id);

//       if (index !== -1) {
//         if (cart[index].quantity > 1) {
//           cart[index].quantity--;
//         } else {
//           cart.splice(index, 1);
//         }
//       }
//     },
//     clearCart(state, merchantId) {
//       if (state.carts[merchantId]) {
//         Vue.set(state.carts, merchantId, []);
//       }
//     }
//   },
//   getters: {
//     cartItems: (state) => (merchantId) => {
//       return state.carts[merchantId] || [];
//     },
//     cartTotal: (state) => (merchantId) => {
//       const cart = state.carts[merchantId] || [];
//       return cart.reduce((total, item) => total + item.price * item.quantity, 0).toFixed(2);
//     },
//     cartCount: (state) => (merchantId) => {
//       const cart = state.carts[merchantId] || [];
//       return cart.reduce((total, item) => total + item.quantity, 0);
//     }
//   }
// });
