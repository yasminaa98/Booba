import * as React from "react";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import "./Signin.css";
import InputBase from "@mui/material/InputBase";
import { styled } from "@mui/material/styles";
import InputLabel from "@mui/material/InputLabel";
import FormControl from "@mui/material/FormControl";
import Button from "@mui/material/Button";
import { useState } from "react";
import ButtonUnstyled from "@mui/base/ButtonUnstyled";
import PropTypes from "prop-types";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import Visibility from "@mui/icons-material/Visibility";
import InputUnstyled, { inputUnstyledClasses } from "@mui/base/InputUnstyled";

const purpl = {
  100: "#F5E1FF",
  200: "#CC99FF",
  400: "#9F40FF",
  500: "#7F00FF",
  600: "#6600CC",
};

const grey = {
  50: "#F3F6F9",
  100: "#E7EBF0",
  200: "#E0E3E7",
  300: "#CDD2D7",
  400: "#B2BAC2",
  500: "#A0AAB4",
  600: "#6F7E8C",
  700: "#3E5060",
  800: "#2D3843",
  900: "#1A2027",
};
const StyledInputRoot = styled("div")(
  ({ theme }) => `
 
  font-weight: 400;
  border-radius: 5px;
  color: ${theme.palette.mode === "light" ? grey[300] : grey[500]};
  background: ${theme.palette.mode === "dark" ? grey[900] : "#fff"};
  border: 1px solid ${theme.palette.mode === "dark" ? purpl[700] : grey[200]};
  box-shadow: 0px 2px  5px #b393d3 ${
    theme.palette.mode === "dark" ? grey[900] : grey[50]
  };
  display: flex;
  align-items: center;
  justify-content: center;


  &.${inputUnstyledClasses.focused} {
    border-color: ${purpl};
    box-shadow: 0px 0px 5px #b393d3, 0px 0px 10px #b393d3, 0px 0px 10px #b393d3, 0px 0px 20px #b393d3;

  }

  &:hover {
    border-color: ${purpl[400]};
  }

  // firefox
  &:focus-visible {
    outline: 0;
  }
`
);

const StyledInputElement = styled("input")(
  ({ theme }) => `
  font-size: 0.8rem;
  font-family: inherit;
  font-weight: 400;
  line-height: 1.5;
  color: ${theme.palette.mode === "dark" ? grey[300] : grey[900]};
  background: inherit;
  border: none;
  border-radius: inherit;
  padding: 12px 12px;
  outline: 0;
`
);

const IconButton = styled(ButtonUnstyled)(
  ({ theme }) => `
  display: inline-flex;
  align-items: center;
  justify-content: center;
  border: none;
  size:10px,
  background: inherit;
  cursor: pointer;
  color: ${theme.palette.mode === "dark" ? grey[300] : grey[700]};
  `
);

const InputAdornment = styled("div")`
  margin: 8px;
  display: inline-flex;
  align-items: center;
  justify-content: center;
`;

const CustomInput = React.forwardRef(function CustomInput(props, ref) {
  const { slots, ...other } = props;
  return (
    <InputUnstyled
      slots={{
        root: StyledInputRoot,
        input: StyledInputElement,
        ...slots,
      }}
      {...other}
      ref={ref}
    />
  );
});

CustomInput.propTypes = {
  slots: PropTypes.shape({
    input: PropTypes.elementType,
    root: PropTypes.elementType,
    textarea: PropTypes.elementType,
  }),
};

function Signin() {
  const [values, setValues] = React.useState({
    firstname: "",
    lastname: "",
    username: "",
    email: "",
    homeAddress: "",
    confirmpassword: "",
    phone: "",
    avgResponseTime: "",
    description: "",
    showPassword: false,
  });

  const handleChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value });
  };

  const handleclickk = (e) => {
    e.preventDefault();
    const user = {
      username: values.username,
      password: values.password,
      firstname: values.firstname,
      lastname: values.lastname,
      email: values.email,
      homeAddress: values.homeAddress,
      phone: values.phone,
      avgResponseTime: values.avgResponseTime,
      description: values.description,
    };
    console.log(user);
    fetch("http://localhost:2023/api/auth/signup", {
      method: "POST",
      headers: { "Content-type": "applicetion/jason" },
      body: JSON.stringify(user),
    }).catch((error) => {
      console.error("Error:", error);
    });
  };

  const handleClickShowPassword = () => {
    setValues({
      ...values,
      showPassword: !values.showPassword,
    });
  };

  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };

  return (
    <Box className="boxx">
      <React.Fragment>
        <Container maxWidth="sm">
          <Box className="main">
            <Box sx={{ width: "276px", padding: "20px" }}>
              <FormControl variant="standard" sx={{ margin: "10px" }}>
                <Box>
                  <InputLabel
                    shrink
                    htmlFor=" First-name "
                    sx={{ color: "gris", "&.Mui-focused": { color: "purple" } }}
                  >
                    First name
                  </InputLabel>
                  <CustomInput
                    onChange={handleChange("firstname")}
                    value={values.firstname}
                    sx={{ marginTop: "20px" }}
                    id="firstname"
                    startAdornment={<InputAdornment> </InputAdornment>}
                  />
                </Box>

                <Box>
                  <InputLabel
                    shrink
                    htmlFor=" Last-name "
                    sx={{
                      color: "gris",
                      marginTop: "75px",
                      "&.Mui-focused": { color: "purple" },
                    }}
                  >
                    Last name
                  </InputLabel>
                  <CustomInput
                    onChange={handleChange("lastname")}
                    value={values.lastname}
                    sx={{ marginTop: "30px" }}
                    id="lastname"
                    startAdornment={<InputAdornment> </InputAdornment>}
                  />
                </Box>

                <Box>
                  <InputLabel
                    shrink
                    htmlFor=" User-name"
                    sx={{
                      color: "gris",
                      marginTop: "145px",
                      "&.Mui-focused": { color: "purple" },
                    }}
                  >
                    User name
                  </InputLabel>
                  <CustomInput
                    onChange={handleChange("username")}
                    value={values.username}
                    sx={{ marginTop: "30px" }}
                    id="username"
                    startAdornment={<InputAdornment> </InputAdornment>}
                  />
                </Box>

                <Box>
                  <InputLabel
                    shrink
                    htmlFor=" Home-adress "
                    sx={{
                      color: "gris",
                      marginTop: "220px",
                      "&.Mui-focused": { color: "purple" },
                    }}
                  >
                    Home Address
                  </InputLabel>
                  <CustomInput
                    onChange={handleChange("homeAddress")}
                    value={values.homeAddress}
                    sx={{ marginTop: "30px" }}
                    id="homeAddress"
                    startAdornment={<InputAdornment> </InputAdornment>}
                  />
                </Box>
                {/*<Box>
                  <InputLabel
                    shrink
                    htmlFor=" confirm-Password"
                    sx={{
                      color: "gris",
                      marginTop: "295px",
                      marginLeft: "0px",
                      "&.Mui-focused": { color: "purple" },
                    }}
                  >
                    confirm Password
                  </InputLabel>
                  <CustomInput
                    sx={{ marginTop: "28px" }}
                    id="confirm-password"
                    type={values.showPassword ? "text" : "password"}
                    value={values.confirmpassword}
                    onChange={handleChange("confirmpassword")}
                    endAdornment={
                      <InputAdornment>
                        <IconButton
                          aria-label="toggle password visibility"
                          onClick={handleClickShowPassword}
                          onMouseDown={handleMouseDownPassword}
                        >
                          {values.showPassword ? (
                            <VisibilityOff />
                          ) : (
                            <Visibility />
                          )}
                        </IconButton>
                      </InputAdornment>
                    }
                  />
                  </Box>*/}
                <Box>
                  <InputLabel
                    shrink
                    htmlFor=" email "
                    sx={{
                      color: "gris",
                      marginTop: "305px",
                      "&.Mui-focused": { color: "purple" },
                    }}
                  >
                    Email
                  </InputLabel>
                  <CustomInput
                    onChange={handleChange("email")}
                    value={values.email}
                    sx={{ marginTop: "30px" }}
                    id="email"
                    startAdornment={<InputAdornment> </InputAdornment>}
                  />
                </Box>
              </FormControl>
            </Box>

            <Box sx={{ width: "276px", padding: "20px" }}>
              <FormControl variant="standard" sx={{ margin: "10px" }}>
                <Box>
                  <InputLabel
                    shrink
                    htmlFor="phone"
                    sx={{ color: "gris", "&.Mui-focused": { color: "purple" } }}
                  >
                    Phone Number
                  </InputLabel>
                  <CustomInput
                    onChange={handleChange("phone")}
                    value={values.phonenumber}
                    sx={{ marginTop: "30px" }}
                    id="phone"
                    startAdornment={<InputAdornment> </InputAdornment>}
                  />
                </Box>

                <Box>
                  <InputLabel
                    shrink
                    htmlFor="Average-Response-Time"
                    sx={{
                      color: "gris",
                      marginTop: "80px",
                      marginLeft: "0px",
                      "&.Mui-focused": { color: "purple" },
                    }}
                  >
                    Average Response Time
                  </InputLabel>
                  <CustomInput
                    onChange={handleChange("avgResponseTime")}
                    value={values.avgResponseTime}
                    sx={{ marginTop: "30px" }}
                    id="avgResponseTime"
                    startAdornment={<InputAdornment> </InputAdornment>}
                  />
                </Box>

                <Box>
                  <InputLabel
                    shrink
                    htmlFor="password-input"
                    sx={{
                      color: "gris",
                      marginTop: "155px",
                      marginLeft: "0px",
                      "&.Mui-focused": { color: "purple" },
                    }}
                  >
                    Password
                  </InputLabel>
                  <CustomInput
                    sx={{ marginTop: "28px" }}
                    id="password"
                    type={values.showPassword ? "text" : "password"}
                    value={values.password}
                    onChange={handleChange("password")}
                    endAdornment={
                      <InputAdornment>
                        <IconButton
                          aria-label="toggle password visibility"
                          onClick={handleClickShowPassword}
                          onMouseDown={handleMouseDownPassword}
                        >
                          {values.showPassword ? (
                            <VisibilityOff />
                          ) : (
                            <Visibility />
                          )}
                        </IconButton>
                      </InputAdornment>
                    }
                  />
                </Box>

                <Box>
                  <InputLabel
                    shrink
                    htmlFor="description"
                    sx={{
                      color: "gris",
                      marginTop: "229px",
                      marginLeft: "0px",
                      "&.Mui-focused": { color: "purple" },
                    }}
                  >
                    Description
                  </InputLabel>
                  <CustomInput
                    onChange={handleChange("description")}
                    value={values.description}
                    sx={{ marginTop: "30px" }}
                    id="description"
                    startAdornment={<InputAdornment> </InputAdornment>}
                  />
                </Box>

                <Button
                  color="secondary"
                  variant="outlined"
                  size="medium"
                  onClick={handleclickk}
                  sx={{
                    mt: 1,
                    mb: 1,
                    marginTop: "100px",
                    width: "100px",
                    marginLeft: "130px",
                  }}
                >
                  register
                </Button>
              </FormControl>
            </Box>
          </Box>
        </Container>
      </React.Fragment>
    </Box>
  );
}

export default Signin;
