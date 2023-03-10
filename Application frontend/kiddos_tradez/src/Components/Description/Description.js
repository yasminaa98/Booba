import useWindowDimensions from '../../hooks/useWindowDimension';
import Typewriter from 'typewriter-effect';
import './Description.css';
import React from 'react';
import Box from '@mui/material/Box';

function Description() {
  const { height } = useWindowDimensions();
  let navHeight = height / 4;

  return (
    <Box
      style={{
        marginTop: '0',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: 'white',
      }}
    >
      <Box
        style={{
          //color: 'black',
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          maxWidth: '1580px',
          overflow: 'auto',
        }}
      >
        <Box className='t' style={{ alignItems: 'center' }}>
          <p>
          <h2>Looking for a hassle-free way to buy, sell, or exchange toys?</h2>
          <h3>Look no further than Kiddos Tradez!</h3>
          <p>With Kiddos Tradez,<br/>
            you can easily browse through a vast selection of pre-loved toys,<br/>
            sell or trade your own gently-used items,<br/>
            and connect with a community of like-minded families who share your passion for play<br/>
             <b className='bold'>So why wait?<br/>
             Sign up for Kiddos Tradez today and discover a whole new world of toy<br/>
                buying, selling, and swapping possibilities!</b> </p></p>
        </Box>
       
        
      </Box>
    </Box>
  );
}

export default Description;
