import product_cart from "../data/product_data";
import "./mainproduct.css";
import * as React from "react";
import "./mainproduct.css";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import { CardActionArea } from "@mui/material";

const Mainproduct = () => {
  const [spacing, setSpacing] = React.useState(2);

  const handleChange = (event) => {
    setSpacing(Number(event.target.value));
  };

  const jsx = `
  <Grid container spacing={${spacing}}>
  `;

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "row",
        flexWrap: "wrap",
        alignItems: "center",
        backgroundColor: "white",
        gap: "20px",
        padding: " 20px",
      }}
    >
      {product_cart.map((value) => (
        <div>
          <Card
            sx={{
              maxWidth: "1000px",
              marginTop: "10px",
              boxShadow: "0 8px 32px rgba(31, 28, 135, 0.37)",
              transition: "all 1s ease-out",
              borderRadius: "10px",
              backgroundColor: "rgba(255,255,255,0.4)",
              "&:hover": {
                backgroundColor: "#6104b794",
                borderRadius: "30px",
                cursor: "pointer",
              },
            }}
          >
            <CardActionArea>
              <CardMedia
                component="img"
                height="100px"
                width="100px"
                image={value.thumb}
                alt="green iguana"
              />

              <CardContent>
                <Typography gutterBottom variant="h6" component="div">
                  {value.name}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  {value.description}
                </Typography>
                <Typography className="price">{value.real_price}</Typography>
              </CardContent>
            </CardActionArea>
          </Card>
        </div>
      ))}
    </div>
  );
};
export default Mainproduct;
