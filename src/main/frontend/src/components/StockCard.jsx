
const logoCss = {
  width: "16px",
  height: "16px",
  display: "inline",
  verticalAlign: "middle",
  marginLeft: "8px",
};

const StockCard = ({ url, logo }) => {

  return (
    <a target="_blank" href={url} rel="noopener noreferrer">
      <img src={logo} alt="screener" style={logoCss} />
    </a>
  );
};

export default StockCard;