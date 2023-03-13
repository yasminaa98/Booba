
 import Tiitle from '../Components/title/Tiitle';
import Description from '../Components/Description/Description';
 import Mainproduct from '../Components/mainproduct/Mainproduct';  
import "../Components/mainproduct/mainproduct.css"
import Footer from '../Components/Footer/Footer';
function Userinterface() {
  
  return ( <div >
     
    <div>
    <Tiitle/>
    <Description/>
    <div ><Mainproduct/></div>
    <Footer/>
    
    </div>
    </div>
    
   
  );
}
  
export default Userinterface;