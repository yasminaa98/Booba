import * as React from "react";
import Drawer from "@mui/material/Drawer";
import ListSubheader from "@mui/material/ListSubheader";
import List from "@mui/material/List";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import Collapse from "@mui/material/Collapse";
import InboxIcon from "@mui/icons-material/MoveToInbox";
import DraftsIcon from "@mui/icons-material/Drafts";
import SendIcon from "@mui/icons-material/Send";
import ExpandLess from "@mui/icons-material/ExpandLess";
import ExpandMore from "@mui/icons-material/ExpandMore";
import StarBorder from "@mui/icons-material/StarBorder";
import Checkbox from "@mui/material/Checkbox";
import { CartState } from "../../context/Context";
import CheckedboxProton from "../CheckedboxProton/CheckedboxProton";
import { Box } from "@mui/system";
import { makeStyles } from "@material-ui/core/styles";
import { Paper } from "@mui/material";
import CheckedboxProtonp from "../CheckedboxProtonp/CheckedboxProtonp";

const Navigator = ({ ages, changeChecked, prices, changePrice }) => {
  const classes = makeStyles({
    paper: {
      width: "250px",
      minHeight: "80vh",
      marginTop: "30px",
      overflow: "hidden",
      backgroundColor: "#f3ecb059",
      boxShadow: "0 8px 32px rgba(31,28,135,0.37) ",
      backdropFilter: " blur(8.5px)",
      borderRadius: "10px",
    },
  });
  const [open, setOpen] = React.useState(true);
  const [openn, setOpenn] = React.useState(true);
  const [opennn, setOpennn] = React.useState(true);
  const {
    state: { toys },
  } = CartState();

  const handleClick = () => {
    setOpen(!open);
  };

  const handleClickk = () => {
    setOpenn(!openn);
  };
  const handleClickkk = () => {
    setOpennn(!opennn);
  };

  return (
    <Drawer
      PaperProps={{
        sx: {
          width: "250px",
          marginTop: "17vh",
          height: "80vh",
          marginLeft: "30px",
          overflow: "hidden",
          backgroundColor: "#f3ecb059",
          boxShadow: "0 8px 32px rgba(31,28,135,0.37) ",
          backdropFilter: " blur(8.5px)",
          borderRadius: "10px",
        },
      }}
      //style={{backgroundColor:'red'}}
      variant="permanent"
    >
      <List component="div" disablePadding classes={{ paper: classes.paper }}>
        <div>
          <ListItemButton onClick={handleClickk} sx={{ px: 3, py: 1 }}>
            <ListItemText primary="Age" sx={{ color: "#4B0082" }} />
            {openn ? <ExpandLess /> : <ExpandMore />}
          </ListItemButton>

          <Collapse in={openn} timeout="auto" unmountOnExit>
            <List component="div" disablePadding>
              {ages.map((age) => (
                <CheckedboxProton
                  key={age.id}
                  age={age}
                  changeChecked={changeChecked}
                />
              ))}
            </List>
          </Collapse>
        </div>
        <div>
          <ListItemButton onClick={handleClickkk} sx={{ px: 3, py: 1 }}>
            <ListItemText primary="Price" sx={{ color: "#4B0082" }} />
            {opennn ? <ExpandLess /> : <ExpandMore />}
          </ListItemButton>
          <Collapse in={opennn} timeout="auto" unmountOnExit>
            <List component="div" disablePadding>
              {prices.map((price) => (
                <CheckedboxProtonp
                  key={price.id}
                  price={price}
                  changePrice={changePrice}
                />
              ))}
            </List>
          </Collapse>
        </div>
      </List>
    </Drawer>
  );
};
export default Navigator;
