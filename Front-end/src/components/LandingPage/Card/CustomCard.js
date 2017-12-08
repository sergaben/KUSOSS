import React, {Component} from 'react';
import { Card, MuiThemeProvider, CardHeader, CardMedia, CardText, CardTitle, FlatButton} from 'material-ui';
import styles from '../landingPage.css';
class CustomCard extends Component {

    render(){
        let cardBoxShadow = {
            boxShadow:'rgba(0, 0, 0, 0.16) 0px 1px 10px 6px, rgba(0, 0, 0, 0.23) 0px 3px 10px',
            marginTop:'2%',
            marginBottom:'2%'
        }
         
        let cardText = {
            maxHeight: '32vh',
            textAlign:'justify'
        }
        const {titleHeader, overlayHeader, srcImage, altImage, cardTitle, cardInsideText, buttonLabel } = this.props;
        return(
            
            <MuiThemeProvider>
            <Card style={cardBoxShadow} className={styles.cardStyle}>
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
                    <p className={styles.readMore}><FlatButton label={buttonLabel}/></p>
                </CardText>
            </Card>
    </MuiThemeProvider>
        )
    }


}

export default CustomCard;