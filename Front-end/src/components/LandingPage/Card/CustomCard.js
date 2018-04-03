import React, {Component} from 'react';
import { Card, MuiThemeProvider, CardHeader, CardMedia, CardText, CardTitle, FlatButton} from 'material-ui';
import styles from '../landingPage.css';
class CustomCard extends Component {
    constructor(){
        super();
        this.state = {
            reduced:true
        }
    }
    toggleCardHeight = () =>{
        const { reduced }=this.state;
        if(reduced){
            this.setState({reduced:false});
        }else{
            this.setState({reduced:true});
        }
    }
    render(){
        let reducedCard = {
            boxShadow:'rgba(0, 0, 0, 0.16) 0px 1px 10px 6px, rgba(0, 0, 0, 0.23) 0px 3px 10px',
            marginTop:'2%',
            marginBottom:'2%',
            overflow: 'hidden',
            position: 'relative',
            maxHeight: '60vh'
        }
        let expandedCard = {
                boxShadow:'rgba(0, 0, 0, 0.16) 0px 1px 10px 6px, rgba(0, 0, 0, 0.23) 0px 3px 10px',
                marginTop:'2%',
                marginBottom:'2%',
                overflow: 'hidden',
                position: 'relative',
        }
        let cardText = {
            textAlign:'justify'
        }
        const {titleHeader, overlayHeader, srcImage, altImage, cardTitle, cardInsideText} = this.props;
        const { reduced } = this.state;
        // const toggleClass;
        return(
            
            <MuiThemeProvider>
            <Card style={reduced ? reducedCard : expandedCard }  >
                <CardHeader
                    title={titleHeader}
                />
                <CardMedia
                    overlay={overlayHeader}
                >
                    <img src={srcImage} alt={altImage}/>
                </CardMedia>
                    <CardTitle title={cardTitle}/>
                <CardText style={cardText}>
                    {cardInsideText}
                    <p className={styles.readMore}><FlatButton label={reduced ? "show more" : "show less"} onClick={this.toggleCardHeight}/></p>
                </CardText>
            </Card>
    </MuiThemeProvider>
        )
    }


}

export default CustomCard;