
import * as React from 'react';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import './Signin.css'
import InputBase from '@mui/material/InputBase';
import { styled } from '@mui/material/styles';
import InputLabel from '@mui/material/InputLabel';
import FormControl from '@mui/material/FormControl';
import Button from '@mui/material/Button';







function Signin() {
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
  
  };
  const BootstrapInput = styled(InputBase)(({ theme }) => ({
    'label + &': {
      marginTop: theme.spacing(3),
    },
    '& .MuiInputBase-input': {
      borderRadius: 5,
      position: 'relative',
      backgroundColor: theme.palette.mode === 'light' ? 'rgba(255, 255, 255, 0.341)' : '#2b2b2b',
      border: '1px solid rgb(153, 80, 180)',
      fontSize: 17,
      width: 'auto',
      padding: '10px 12px',
      transition: theme.transitions.create([
        'border-color',
        'background-color',
        'box-shadow',
      ]),
      // Use the system font instead of the default Roboto font.
      fontFamily: [
        '-apple-system',
        'BlinkMacSystemFont',
        '"Segoe UI"',
        'Roboto',
        '"Helvetica Neue"',
        'Arial',
        'sans-serif',
        '"Apple Color Emoji"',
        '"Segoe UI Emoji"',
        '"Segoe UI Symbol"',
      ].join(','),
      '&:focus': {
        boxShadow: '0px 0px 5px #b393d3, 0px 0px 10px #b393d3, 0px 0px 10px #b393d3, 0px 0px 20px #b393d3',
        borderColor:"purple",
      },
      
    },
  }));




  return (
    <Box className="boxx">
        <React.Fragment>
          <Container maxWidth="sm" >
            <Box className="main" >
        
            <Box sx={{width:"276px",padding:"20px"}} >
                    <FormControl variant="standard" sx={{margin:"10px"}}>
                                      <Box>
                                            <InputLabel shrink htmlFor=" First-name " sx={{color:"gris",'&.Mui-focused': {color: "purple"}}}>
                                            First name 
                                            </InputLabel>
                                             <BootstrapInput  id=" First-name " />
                                        </Box>


                                      
                                        <Box>
                                            <InputLabel shrink htmlFor=" Last-name " sx={{color:"gris",marginTop:"75px",'&.Mui-focused': {color: "purple"}}}>
                                           Last name
                                            </InputLabel>
                                             <BootstrapInput  id=" Last-name " />
                                        </Box>

                                        <Box>
                                            <InputLabel shrink htmlFor=" User-name" sx={{color:"gris",marginTop:"145px",'&.Mui-focused': {color: "purple"}}}>
                                            User name
                                            </InputLabel>
                                             <BootstrapInput  id=" User-name " />
                                        </Box>

                                        <Box>
                                            <InputLabel shrink htmlFor=" Home-adress " sx={{color:"gris",marginTop:"215px",'&.Mui-focused': {color: "purple"}}}>
                                            Home Address
                                            </InputLabel>
                                             <BootstrapInput  id=" Home-adress " />
                                        </Box>
                                        <Box>
                                              <InputLabel shrink  htmlFor=" confirm-Password" sx={{color:"gris" ,marginTop:"285px", marginLeft:"0px", '&.Mui-focused': {color: "purple"}}}>
                                                confirm Password
                                              </InputLabel>
                                              <BootstrapInput  id="confirm-Password"  />
                                          
                                          </Box>

                                  </FormControl>
                    </Box>

                    <Box sx={{width:"276px",padding:"20px"}} >
                    <FormControl variant="standard" sx={{margin:"10px"}}>
                                      <Box>
                                            <InputLabel shrink htmlFor="Phone-number" sx={{color:"gris",'&.Mui-focused': {color: "purple"}}}>
                                            Phone Number
                                            </InputLabel>
                                             <BootstrapInput  id="Phone-number" />
                                        </Box>


                                      
                                          <Box>
                                              <InputLabel shrink  htmlFor="Average-Response-Time" sx={{color:"gris",marginTop:"75px" , marginLeft:"0px", '&.Mui-focused': {color: "purple"}}}>
                                               Average Response Time
                                              </InputLabel>
                                              <BootstrapInput  id="Average-Response-Time"  />
                                          
                                          </Box>

                                          <Box>
                                              <InputLabel shrink  htmlFor="password-input" sx={{color:"gris",marginTop:"145px" , marginLeft:"0px", '&.Mui-focused': {color: "purple"}}}>
                                               Password
                                              </InputLabel>
                                              <BootstrapInput  id="password-input"  />
                                          
                                          </Box>

                                          <Box>
                                              <InputLabel shrink  htmlFor="Description" sx={{color:"gris" ,marginTop:"215px", marginLeft:"0px",'&.Mui-focused': {color: "purple"}}}>
                                               Description
                                              </InputLabel>
                                              <BootstrapInput  id="Description"  />
                                          
                                          </Box>
                                          <Button
                              type="submit"
                              color="secondary"
                              variant="outlined"
                              size="medium"
                               sx={{ mt: 1, mb: 1,marginTop:"100px", width:"100px" ,marginLeft:"130px"}}
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

//height: 60%;
//width: 80%;