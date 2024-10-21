const { ref, reactive, onMounted } = Vue;
	
	const app = Vue.createApp({
	    setup() {
	        const uniqueGenders = ref([]);  // Assuming these are pre-populated
	        const uniqueBrands = ref([]);   // Assuming these are pre-populated
	        const uniqueCurrencies = ref([]); // Assuming these are pre-populated
	        const filteredProducts = ref([]);  // Assuming these are pre-populated
	        const similarProducts = Vue.reactive({
				products: {}
			});
	        const filtersApplied = ref(false);

	        onMounted(() => {
	            // Retrieve the values from the hidden inputs
	            uniqueGenders.value = $('#uniqueGenders').val().replace(/\[|\]/g, '').split(',').map(item => item.trim());
	            uniqueBrands.value = $('#uniqueBrands').val().replace(/\[|\]/g, '').split(',').map(item => item.trim());
	            uniqueCurrencies.value = $('#uniqueCurrencies').val().replace(/\[|\]/g, '').split(',').map(item => item.trim());
	            similarProducts.products = {};
	            similarProducts.products = $('#similarProducts').val();
	        });

	        function sendSelectedValuesToBackend() {
	            const selectedValues = {
	                selectedGender: [],
	                selectedBrand: [],
	                selectedCurrency: []
	            };
				
	            const checkboxes = document.querySelectorAll('.checkbox:checked');
	            checkboxes.forEach(checkbox => {
	                const value = checkbox.value;
	                const parentClassList = checkbox.parentElement.classList;

	                if (parentClassList.contains('gender')) {
	                    selectedValues.selectedGender.push(value);
	                } else if (parentClassList.contains('brand')) {
	                    selectedValues.selectedBrand.push(value);
	                } else if (parentClassList.contains('currency')) {
	                    selectedValues.selectedCurrency.push(value);
	                }
	            });

	            axios.post('https://localhost:9002/occ/v2/electronics/getFilteredProduct', selectedValues, {
	                    headers: { 'X-Requested-With': 'XMLHttpRequest' }
	                })
	                .then(response => {
	                    // Update the similarProducts data
	                    filteredProducts.value = response.data;
	                    filtersApplied.value = true;
	                    console.log(response.data);
	                })
	                .catch(error => {
	                    console.error('Error sending selected values to the backend.', error);
	                });
	        }
	
	        return {
	            uniqueGenders,
	            uniqueBrands,
	            uniqueCurrencies,
	            filteredProducts,
	            similarProducts,
	            filtersApplied,
	            sendSelectedValuesToBackend
	        };
	    }
	});
	
	app.mount('#app');