import "./Singleproduct.css";
import * as React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import { CardActionArea } from "@mui/material";

const Singleproduct = ({ item: { name, description, price, thumb } }) => {
  console.log("name is here---------------------", name);
  const [spacing, setSpacing] = React.useState(2);

  const handleChange = (event) => {
    setSpacing(Number(event.target.value));
  };

  const jsx = `
  <Grid container spacing={${spacing}}>
  `;

  return (
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
            //transition: "border 5s ",
          },
        }}
      >
        <CardActionArea>
          <CardMedia
            component="img"
            height="100px"
            width="100px"
            image={thumb}
            alt="green iguana"
          />

          <CardContent>
            <Typography gutterBottom variant="h6" component="div">
              {name}
            </Typography>
            <Typography variant="body2" color="text.secondary">
              {description}
            </Typography>
            <Typography className="price">{price}</Typography>
          </CardContent>
        </CardActionArea>
      </Card>
    </div>
  );
};

export default Singleproduct;
