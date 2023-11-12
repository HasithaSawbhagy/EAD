import { Box, Button } from "@mui/material";
import axios from "axios";
import { DataGrid, GridToolbar } from "@mui/x-data-grid";
import { tokens } from "../../theme";
import { mockDataContacts } from "../../data/mockData";
import Header from "../../components/Header";
import React, { useState, useEffect } from "react";
import { useTheme } from "@mui/material";
// import InventoryService from "../../services/InventoryService";

const Contacts = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

  

  const [products, setProducts] = useState([]);

  // Fetch all products
  useEffect(() => {
    axios
      .get("http://localhost:8081/api/product")
      .then((response) => {
        setProducts(response.data);
      })
      .catch((error) => {
        console.error("Error fetching products:", error);
      });
  }, []);

  const handleUpdate = (id) => {
    // Implement the logic to update the product on the backend
    console.log(`Update product with ID: ${id}`);
  };

  const handleDelete = (id) => {
  // Implement the logic to delete the product on the backend
  console.log(`Delete product with ID: ${id}`);

  // Send a DELETE request to the backend endpoint
  axios.delete(`http://localhost:8081/api/product/delete/${id}`)
    .then((response) => {
      console.log("Product deleted successfully:", response.data);

      // After successful deletion, update the state or refetch the data
      axios.get("http://localhost:8081/api/product")
        .then((fetchResponse) => {
          setProducts(fetchResponse.data);
        })
        .catch((fetchError) => {
          console.error("Error fetching products:", fetchError);
        });
    })
    .catch((error) => {
      console.error("Error deleting product:", error);
    });
  };

  const columns = [
    { field: "id", headerName: "ID", flex: 0.5 },
    { field: "name", headerName: "Name" },
    {
      field: "description",
      headerName: "Description",
      flex: 1,
      cellClassName: "name-column--cell",
    },
    {
      field: "quantity",
      headerName: "Quantity",
      type: "number",
      headerAlign: "left",
      align: "left",
    },
    {
      field: "price",
      headerName: "Price",
      flex: 1,
    },
    {
      field: "update",
      headerName: "Update",
      flex: 1,
      renderCell: (params) => (
        <Button
          variant="contained"
          style={{ backgroundColor: "#2e7c67" }}
          onClick={() => handleUpdate(params.row.id)}
        >
          Update
        </Button>
      ),
    },
    {
      field: "delete",
      headerName: "Delete",
      flex: 1,
      renderCell: (params) => (
        <Button
          variant="contained"
          style={{ backgroundColor: "#2e7c67" }}
          onClick={() => handleDelete(params.row.id)}
        >
          Delete
        </Button>
      ),
    },
  ];

  return (
    <Box m="20px">
      <Header title="Inventory Data" subtitle="List of existing inventory" />
      <Box
        m="40px 0 0 0"
        height="75vh"
        sx={
          {

          }
        }
      >
        <DataGrid
          rows={products}
          columns={columns}
          components={{ Toolbar: GridToolbar }}
        />
      </Box>
    </Box>
  );
};

export default Contacts;
