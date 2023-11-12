import axios from "axios";
const inventory_rest_API_URL = 'http://localhost:8081/api/product';
class InventoryService {
    getAllProducts() {
        return axios.get(inventory_rest_API_URL);
    }
}

export default new InventoryService();