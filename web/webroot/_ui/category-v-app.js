const STORAGE_KEY = 'selectedValues';

const app = new Vue({
  el: '#app',
  data: {
    selectedValues: [],
  },
  methods: {
    handleCheckboxClick(item) {
      if (this.selectedValues.includes(item)) {
        const index = this.selectedValues.indexOf(item);
        if (index !== -1) {
          this.selectedValues.splice(index, 1);
        }
      } else {
        this.selectedValues.push(item);
      }
    },
   
    submitSelectedValues() {
      const form = document.createElement('form');
      form.setAttribute('method', 'POST');
      form.setAttribute('action', '/training/similar');

      this.selectedValues.forEach(value => {
        const input = document.createElement('input');
        input.setAttribute('type', 'hidden');
        input.setAttribute('name', 'selectedValues');
        input.setAttribute('value', value);
        form.appendChild(input);
      });

      document.body.appendChild(form);
      form.submit();
    }
  }
}); 