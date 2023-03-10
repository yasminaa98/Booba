import { createTheme, ThemeProvider } from "@mui/material/styles";
import useMediaQuery from "@mui/material/useMediaQuery";
import CssBaseline from "@mui/material/CssBaseline";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Link from "@mui/material/Link";
import Header from "../../Components/Header/Header";
import imge from "../../Asserts/Images/signin.png";
import Navbar from "../../Components/Navbar/Navbar.js";
import { height } from "@mui/system";
import React, { useEffect, useState } from "react";
import { CartState } from "../../context/Context";
import Navigator from "../../Components/Navigator/Navigator";
import Content from "../../Components/Content/Content";
import product_cart from "../../Components/data/product_data";
import Emptyview from "../../Components/EmptyView/Emptyview";
import Crousell from "../../Components/Crousell/Crousell";
import "./Discover.css";

let theme = createTheme({
  mixins: {
    toolbar: {
      minHeight: 80,
    },
  },
});

const drawerWidth = 256;
/*--------------------------------------------------------*/
export default function Discover() {
  const [ages, setages] = useState([
    { id: 1, checked: false, label: "0-2" },
    { id: 2, checked: false, label: "2-4" },
    { id: 3, checked: false, label: "4-6" },
    { id: 4, checked: false, label: "+6" },
  ]);

  const [prices, setprices] = useState([
    { id: 1, checked: false, label: "0-5" },
    { id: 2, checked: false, label: "5-10" },
    { id: 3, checked: false, label: "10-15" },
    { id: 4, checked: false, label: "+15" },
  ]);

  /****************************************************/
  const [list, setList] = useState(product_cart);
  const [resultsFound, setResultsFound] = useState(true);
  /****************************************************/
  const handleChangeChecked = (id) => {
    const agesStateList = ages;
    const changeCheckedages = agesStateList.map((item) =>
      item.id === id ? { ...item, checked: !item.checked } : item
    );
    setages(changeCheckedages);
  };
  const handleChangePrice = (id) => {
    const pricesStateList = prices;
    const changeCheckedprices = pricesStateList.map((itemm) =>
      itemm.id === id ? { ...itemm, checked: !itemm.checked } : itemm
    );
    setprices(changeCheckedprices);
  };

  /****************************************************/

  const agesChecked = ages
    .filter((item) => item.checked)
    .map((item) => {
      if (item.label !== "+6") {
        const [minAge, maxAge] = item.label.split("-");
        return {
          id: item.id,
          minAge: parseInt(minAge.trim()),
          maxAge: parseInt(maxAge.trim()),
        };
      } else if (item.label == "+6") {
        const minAge = parseInt(item.label.replace("+", "").trim());
        const maxAge = 999;
        return {
          minAge: minAge,
          maxAge: maxAge,
        };
      }
    });

  const pricesChecked = prices
    .filter((itemm) => itemm.checked)
    .map((itemm) => {
      if (itemm.label !== "+15") {
        const [minPrice, maxPrice] = itemm.label.split("-");
        return {
          id: itemm.id,
          minPrice: parseInt(minPrice.trim()),
          maxPrice: parseInt(maxPrice.trim()),
        };
      } else if (itemm.label == "+15") {
        const minPrice = parseInt(itemm.label.replace("+", "").trim());
        const maxPrice = 999;
        return {
          minPrice: minPrice,
          maxPrice: maxPrice,
        };
      }
    });

  /****************************************************/
  const applyFilters = () => {
    let updatedList = product_cart;

    if (agesChecked.length) {
      updatedList = updatedList.filter((item) => {
        const itemAge = item.age;
        const ageInRange = agesChecked.some(
          (age) => itemAge >= age.minAge && itemAge <= age.maxAge
        );
        return ageInRange;
      });
    } else if (pricesChecked.length) {
      updatedList = updatedList.filter((itemm) => {
        const itemmPrice = itemm.price;
        const priceInRange = pricesChecked.some(
          (price) =>
            itemmPrice >= price.minPrice && itemmPrice <= price.maxPrice
        );
        return priceInRange;
      });
    }

    setList(updatedList);
    !updatedList.length ? setResultsFound(false) : setResultsFound(true);
  };

  /****************************************************/

  useEffect(() => {
    applyFilters();
  }, [ages, prices]);

  /*--------------------------------------------------------*/
  const [mobileOpen, setMobileOpen] = React.useState(false);
  const isSmUp = useMediaQuery(theme.breakpoints.up("sm"));

  const handleDrawerToggle = () => {
    console.log(mobileOpen);
    setMobileOpen(!mobileOpen);
    console.log(mobileOpen);
  };
  return (
    <ThemeProvider theme={theme}>
      <Box
        className="Boxii"
        sx={{
          display: "flex",
          backgroundImage: `url(${imge})`,
          backgroundRepeat: "no-repeat",
          backgroundSize: "cover",
          backgroundAttachment: "fixed",
        }}
      >
        <Box
          component="nav"
          sx={{ width: { sm: drawerWidth }, flexShrink: { sm: 0 } }}
        >
          {(isSmUp || !mobileOpen) && (
            <Navigator
              variant="temporary"
              open={mobileOpen}
              onClose={handleDrawerToggle}
              ages={ages}
              changeChecked={handleChangeChecked}
              prices={prices}
              changePrice={handleChangePrice}
            />
          )}
        </Box>

        <Box
          className="red"
          sx={{
            display: "flex",
            justifyContent: "center",
            flexDirection: "column",
            width: "100%",
            maxWidth: "150vh",
            marginTop: "2vh",
            marginLeft: "8vh",
            marginRight: "4vh",
          }}
        >
          <Header onDrawerToggle={handleDrawerToggle} />
          <div
            style={{
              overflow: "auto",
              display: "flex",
              height: "280px",
              marginTop: "20px",
              padding: "0px",
              backgroundColor: "#f3ecb059",
              boxShadow: "0 8px 32px rgba(31,28,135,0.37) ",
              borderRadius: "10px",
            }}
          >
            <Crousell />
          </div>

          <div
            style={{
              overflow: "auto",
              display: "flex",
              minHeight: "80vh",
              marginTop: "20px",
              backgroundColor: "#f3ecb059",
              boxShadow: "0 8px 32px rgba(31,28,135,0.37) ",
              borderRadius: "10px",
            }}
          >
            {<Content list={list} />}
          </div>
        </Box>
      </Box>
    </ThemeProvider>
  );
}
