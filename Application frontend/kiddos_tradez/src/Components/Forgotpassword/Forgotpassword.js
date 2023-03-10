
import * as React from 'react';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import './Forgotpassword.css'
import InputBase from '@mui/material/InputBase';
import { styled } from '@mui/material/styles';
import InputLabel from '@mui/material/InputLabel';
import FormControl from '@mui/material/FormControl';
import Button from '@mui/material/Button';
import {Typography}  from '@mui/material';
import { textAlign } from '@mui/system';








function Forgotpassword() {
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
      fontSize: 15,
      width: 246,
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
    <Box className="boxxF">
        <React.Fragment>
          <Container maxWidth="xs" >
            <Box className="mainF" >
                    <FormControl variant="standard" sx={{padding:"60px"}}>
                    <Typography style={{ marginTop:"0px",fontSize: 15,color: "#fefdfe",textShadow:" 0px 0px 5px #b393d3, 0px 0px 10px #b393d3, 0px 0px 10px #b393d3,0px 0px 20px #b393d3",fontFamily: "Ink Free" }}
                                >
                                <h1>Forgot Password </h1></Typography>
                                <br/>
                                <Typography style={{ fontSize: 12 ,color: 'gray', textAlign:"start" }}>Lost your password ?<br/>
                                 Please enter your email address. You will receive a link to create a new password via email.</Typography><br/>
                               
            
                                      <Box style={{display:"flex",flexDirection:"center"}}>

                                            <InputLabel shrink htmlFor=" email" sx={{color:"gris",  marginTop:"160px",marginLeft:"25px", padding:"50px",'&.Mui-focused': {color: "purple"}}}>
                                           Email
                                            </InputLabel>
                                             <BootstrapInput  id=" Email" />
                                        </Box>

                                        <Button
                              type="submit"
                              color="secondary"
                              variant="outlined"
                               sx={{ mt: 2, mb: 2,marginLeft:"0px" }}
                              >
                              Send email
                              </Button>
                                      
                                  </FormControl>

            </Box>
          </Container>
        </React.Fragment>
        </Box>
      );
    
    

}

export default Forgotpassword;

