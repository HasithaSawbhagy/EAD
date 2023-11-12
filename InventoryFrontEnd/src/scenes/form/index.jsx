import React, { useState } from 'react';
import { TextField, Button, Box, Typography, Container } from '@mui/material';
import axios from 'axios';

const CreateProductForm = () => {
  const [productRequest, setProductRequest] = useState({
    name: '',
    description: '',
    quantity: 0,
    price: 0.0,
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setProductRequest((prevProductRequest) => ({
      ...prevProductRequest,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Make a POST request to create a new product
    axios.post('http://localhost:8081/api/product', productRequest)
      .then(response => {
        console.log('Product created successfully:', response.data);
        // You can perform additional actions after successful creation
      })
      .catch(error => {
        console.error('Error creating product:', error);
      });
  };

  return (
    <Container component="main" maxWidth="xs">
      <Box
        sx={{
          marginTop: 8,
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
        }}
      >
        <Typography component="h1" variant="h5">
          Create a New Product
        </Typography>
        <form onSubmit={handleSubmit}>
          <TextField
            margin="normal"
            fullWidth
            label="Name"
            name="name"
            value={productRequest.name}
            onChange={handleInputChange}
          />

          <TextField
            margin="normal"
            fullWidth
            label="Description"
            name="description"
            value={productRequest.description}
            onChange={handleInputChange}
          />

          <TextField
            margin="normal"
            fullWidth
            label="Quantity"
            type="number"
            name="quantity"
            value={productRequest.quantity}
            onChange={handleInputChange}
          />

          <TextField
            margin="normal"
            fullWidth
            label="Price"
            type="number"
            name="price"
            value={productRequest.price}
            onChange={handleInputChange}
          />

          {/* Add other fields as needed */}

          <Button type="submit" fullWidth variant="contained" sx={{ mt: 3, mb: 2 }}>
            Create Product
          </Button>
        </form>
      </Box>
    </Container>
  );
};

export default CreateProductForm;
