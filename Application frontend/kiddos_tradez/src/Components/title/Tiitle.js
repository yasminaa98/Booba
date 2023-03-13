import './Title.css';
import useWindowDimensions from '../../hooks/useWindowDimension';
import Typewriter from 'typewriter-effect';
import React from 'react';
import Box from '@mui/material/Box';

function Title() {


  return (
    <Box className="ph"
      style={{
        marginTop: '120px',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor:"#F3ECB0"
        
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
        <Box className='title1' style={{ alignItems: 'center' }}>
          WE ARE
        </Box>
        <br />
        <br />
        <Box className='title2'>
          <Typewriter
            options={{
              autoStart: true,
              loop: true,
              delay: 40,
              strings: [
                'Interactive',
                'Varied',
                'Well-organized',
                'Educational',
                'Enjoyable',
              ],
            }}
          />
        </Box>
        <br />
        <br />
        <div className='title2'>We are Kiddos Tradez</div>
      </Box>
    </Box>
  );
}

export default Title;
