import React from "react";
import { FormControlLabel, ListItemButton, ListItemIcon } from "@mui/material";
import Checkbox from "@mui/material/Checkbox";

import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles({
  checked: {},
  wrap: {
    width: "100%",
    display: "flex",
    flexDirection: "row-reverse",
    justifyContent: "space-between",
    alignItems: "center",
    marginLeft: 0,
  },
  label: {
    fontSize: ".8rem",
    fontFamily: "cursive ",
    color: "#344D67",
  },
});

const CheckedboxProton = ({ changeChecked, age }) => {
  console.log("checkboxproton-------", age);
  const classes = useStyles();
  const { checked, label, id } = age;
  console.log(checked);
  return (
    <div>
      <ListItemButton sx={{ pl: 4 }} autoFocus>
        <FormControlLabel
          classes={{
            label: classes.label,
            root: classes.wrap,
          }}
          control={
            <Checkbox
              classes={{
                checked: classes.checked,
                root: classes.root,
              }}
              size="small"
              checked={checked}
              onClick={() => changeChecked(id)}
              //  onChange={() => changeChecked(id)}
              inputProps={{ "aria-label": "checkbox with small size" }}
              color="secondary"
            />
          }
          label={label}
        />
      </ListItemButton>
    </div>
  );
};

export default CheckedboxProton;
