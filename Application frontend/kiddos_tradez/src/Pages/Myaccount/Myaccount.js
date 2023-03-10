import * as React from 'react';
import Box from '@mui/material/Box';
import './Myaccount.css'
import Button from '@mui/material/Button';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import InputBase from '@mui/material/InputBase';
import { styled } from '@mui/material/styles';
import InputLabel from '@mui/material/InputLabel';
import FormControl from '@mui/material/FormControl';
import {Typography}  from '@mui/material';




function Myaccount() {
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
<Box style={{marginTop: '5px',display: 'flex',justifyContent: 'center', alignItems: 'center' }} >
  <Box style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', maxWidth: '100%', overflow: 'auto', }}> 
    <Box className="box1" >
       <Box className="mainContainer">
    
                <Box className="inputcontainer" >
                    <Box className="logo"></Box>
                        <Box>
                            <h2 id="welcome-heading">WELCOME BACK</h2>
                         </Box>
                </Box>


                 <Box  sx={{ height: "100%",width: "50%"}}>
                        
                    <Box className="logincontainer" >
                              
                              <Box className="login" >
                                  <h2 id="login-heading">Login</h2>
                              </Box>
                                              
                              <Box component="form" onSubmit={handleSubmit} noValidate sx={{ display: 'grid', margin:"0px" ,gridTemplateColumns: { sm: '1fr 1fr' }, gap: 2,}}>
                          
                                 <FormControl variant="standard" sx={{margin:"0px"}}>
                                      <Box>
                                            <InputLabel shrink htmlFor="email-input" sx={{color:"gris",'&.Mui-focused': {color: "purple"}}}>
                                            Email
                                            </InputLabel>
                                             <BootstrapInput  id="email-input" />
                                        </Box>


                                      
                                          <Box>
                                              <InputLabel shrink  htmlFor="password-input" sx={{color:"gris" ,margin:"75px", marginLeft:"0px", '&.Mui-focused': {color: "purple"}}}>
                                               Password
                                              </InputLabel>
                                              <BootstrapInput  id="password-input"  />
                                          
                                          </Box>

                                  </FormControl>
                                    
                              </Box>



                              <FormControlLabel 
                                control={
                                <Checkbox value="remember" color='secondary' sx={{color:"purple"}} />}
                                label={
                                <Typography
                                variant="body1"
                                style={{ fontFamily: 'Arial', fontSize: 13 ,color: 'gray', }}
                                >
                                  Remember me
                                </Typography>}
                              />
                          
                              <Button
                              type="submit"
                              color="secondary"
                              variant="outlined"
                               sx={{ mt: 2, mb: 2,marginLeft:"0px" }}
                              >
                              Sign In
                              </Button>


                              <Grid container >
                                  <Grid item xs>
                                      <Link href="/myaccount/forgotpassword" variant="body2" underline="hover" sx={{color:"purple"}}>
                                        Forgot password?
                                      </Link>
                                  </Grid>
                                  <Grid item>
                                      <Link href="/myaccount/signin"  variant="body2" underline="hover" sx={{color:"purple"}}>
                                        {"Don't have an account? Sign Up"}
                                      </Link>
                                  </Grid>
                               </Grid>
                             
                  
                               
                        </Box>   
                    </Box>
       </Box>                    
    </Box>            
  </Box>
</Box> 
     
     
  );
}
export default  Myaccount;



