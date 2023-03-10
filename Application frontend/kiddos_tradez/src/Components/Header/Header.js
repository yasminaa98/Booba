import * as React from "react";
import PropTypes from "prop-types";
import AppBar from "@mui/material/AppBar";
import Grid from "@mui/material/Grid";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import Toolbar from "@mui/material/Toolbar";
import "./Header.css";
import Navbar from "../Navbar/Navbar";
import Content from "../Content/Content";
import SearchIcon from "@mui/icons-material/Search";
import InputBase from "@mui/material/InputBase";
import { styled, alpha } from "@mui/material/styles";
import { Box } from "@mui/system";

function Header(props) {
  const Search = styled("div")(({ theme }) => ({
    position: "relative",
    borderRadius: theme.shape.borderRadius,
    borderRadius: "50%",
    backgroundColor: alpha(theme.palette.common.white, 0.25),
    "&:hover": {
      backgroundColor: alpha(theme.palette.common.white, 0.25),
    },

    height: "40px",
    [theme.breakpoints.up("sm")]: {
      marginLeft: theme.spacing(1),
      width: "auto",
    },
  }));

  const SearchIconWrapper = styled("div")(({ theme }) => ({
    padding: theme.spacing(0, 1.2),
    height: "100%",
    position: "absolute",
    pointerEvents: "none",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
  }));

  const StyledInputBase = styled(InputBase)(({ theme }) => ({
    "& .MuiInputBase-input": {
      padding: theme.spacing(0, 0, 0, 0),
      // vertical padding + font size from searchIcon
      paddingLeft: `calc(1em + ${theme.spacing(3)})`,
      transition: theme.transitions.create("width"),

      [theme.breakpoints.up("sm")]: {
        width: "1px",
        "&:focus": {
          width: "12ch",
        },
      },
    },
  }));

  const { onDrawerToggle } = props;
  const buildindlist = [
    "Blocks",
    "Magnetic tiles",
    "Marble runs",
    "Construction sets",
  ];

  return (
    <React.Fragment>
      <AppBar
        sx={{
          backgroundColor: "#f3ecb059",
          boxShadow: "0 8px 32px rgba(31,28,135,0.37) ",
          backdropFilter: " blur(8.5px)",
          borderRadius: "10px",
          marginTop: "15vh",
        }}
        position="relative"
        elevation={4}
      >
        <Toolbar>
          <Grid sx={{ display: { sm: "none", xs: "block" } }} item>
            <IconButton
              color="purple"
              aria-label="open drawer"
              onClick={onDrawerToggle}
              edge="start"
            >
              <MenuIcon />
            </IconButton>
          </Grid>

          <Grid container sx={{ display: "flex", flexDirection: "column" }}>
            <Grid
              item
              sx={{
                marginLeft: "8vh",
                marginRight: "4vh",
                display: "flex",
                justifyContent: "center",
              }}
            >
              <Navbar />
            </Grid>
          </Grid>

          <Grid>
            <Search>
              <SearchIconWrapper>
                <SearchIcon sx={{ color: "gray" }} />
              </SearchIconWrapper>
              <StyledInputBase
                placeholder="Search…"
                inputProps={{ "aria-label": "search" }}
              />
            </Search>
          </Grid>
        </Toolbar>
      </AppBar>
    </React.Fragment>
  );
}

Header.propTypes = {
  onDrawerToggle: PropTypes.func.isRequired,
};

export default Header;
