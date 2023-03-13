import * as React from "react";
import Box from "@mui/material/Box";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import KeyboardDoubleArrowRightIcon from "@mui/icons-material/KeyboardDoubleArrowRight";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import logoo from "../../Asserts/Logos/kiddostardez.png";
import "./Footer.css";
import logo from "../../Asserts/Logos/kiddostardez.png";
import { IconButton } from "@mui/material";
import { Facebook } from "@mui/icons-material";
import { Instagram } from "@mui/icons-material";
import { LinkedIn } from "@mui/icons-material";
const Footer = () => {
  return (
    <Box>
      <footer class="footer-distributed">
        <div class="footer-left">
          <Box>
            <img
              src={logo}
              alt="Logo"
              style={{ height: "70px", margin: "10px", flexGrow: 1 }}
            />
          </Box>
        </div>

        <div class="footer-center">
          <div>
            <IconButton component={Link} to="/" aria-label="Facebook">
              <Facebook style={{ fontSize: 30, color: "white" }} />
            </IconButton>
          </div>

          <div>
            <IconButton component={Link} to="/" aria-label="Instagram">
              <Instagram style={{ fontSize: 30, color: "white" }} />
            </IconButton>
          </div>
          <div>
            <IconButton component={Link} to="/" aria-label="LinkedIn">
              <LinkedIn style={{ fontSize: 30, color: "white" }} />
            </IconButton>
          </div>
        </div>

        <div class="footer-right">
          <div
            style={{
              color: "white",
              display: "flex",
              flexDirection: "row",
            }}
          >
            <KeyboardDoubleArrowRightIcon />
            <Link
              href="/"
              variant="body2"
              underline="hover"
              sx={{ color: "white" }}
            >
              Home
            </Link>
          </div>
          <div
            style={{
              color: "white",
              display: "flex",
              flexDirection: "row",
            }}
          >
            <KeyboardDoubleArrowRightIcon />
            <Link
              href="/"
              variant="body2"
              underline="hover"
              sx={{ color: "white" }}
            >
              About us
            </Link>
          </div>
          <div
            style={{
              color: "white",
              display: "flex",
              flexDirection: "row",
            }}
          >
            <KeyboardDoubleArrowRightIcon />
            <Link
              href="/"
              variant="body2"
              underline="hover"
              sx={{ color: "white" }}
            >
              Discover
            </Link>
          </div>
          <div
            style={{
              color: "white",
              display: "flex",
              flexDirection: "row",
            }}
          >
            <KeyboardDoubleArrowRightIcon />
            <Link
              href="/"
              variant="body2"
              underline="hover"
              sx={{ color: "white" }}
            >
              My account
            </Link>
          </div>
        </div>
      </footer>
    </Box>
  );
};
export default Footer;

{
  /* <div style={{color:"purple", display:"flex", alignItems:"center", backgroundColor:"red"}} > 
          <KeyboardDoubleArrowRightIcon />
          <Link href="/" variant="body2" underline="hover" sx={{color:"purple"}}> Home</Link>
          <KeyboardDoubleArrowRightIcon />
          <Link href="/" variant="body2" underline="hover" sx={{color:"purple"}}> Home</Link>
          <KeyboardDoubleArrowRightIcon />
          <Link href="/" variant="body2" underline="hover" sx={{color:"purple"}}> Home</Link>
          <KeyboardDoubleArrowRightIcon />
          <Link href="/" variant="body2" underline="hover" sx={{color:"purple"}}> Home</Link>
        </div>  */
}
