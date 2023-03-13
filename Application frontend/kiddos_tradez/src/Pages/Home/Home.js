
import useWindowDimensions from '../../hooks/useWindowDimension';
import Typewriter from 'typewriter-effect';
import React from 'react';
import Box from '@mui/material/Box';

function Home() {
  const { height } = useWindowDimensions();
  let navHeight = height / 4;

  return (
    <Box
      style={{
        marginTop: '120px',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F3ECB0',
      }}
    >
      <Box
        style={{
          color: 'white',
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          maxWidth: '1580px',
          overflow: 'auto',
        }}
      >
        <h1 >home</h1>
        <h1 >home</h1>
        <h1 >home</h1>
        <h1 >home</h1>
        <h1 >home</h1>
        <h1 >home</h1>
        <h1 >home</h1>
        <h1 >home</h1>
        <h1 >home</h1>
        <h1 >home</h1>
        <h1 >home</h1>
        <h1 >home</h1>
        <h1 >home</h1>
        <h1 >home</h1>
        <h1 >home</h1>
        <h1 >home</h1>
        <h1 >home</h1>
        <h1 >home</h1>
        

        

        

        

        

        

        
        

      </Box>
    </Box>
  );
}

export default Home;