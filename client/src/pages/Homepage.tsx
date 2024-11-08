import { Button, Box, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";

const Homepage = () => {
  const navigate = useNavigate();

  const handleLoginClick = () => {
    navigate("/login");
  };

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
      minHeight="100vh"
      bgcolor="linear-gradient(to bottom, #F9F7EF, #F2F6F2)"
    >
      <Typography variant="h4" component="p" gutterBottom>
        MainPage
      </Typography>
      <Button
        variant="contained"
        color="primary"
        onClick={handleLoginClick}
        sx={{
          mt: 2,
          px: 4,
          py: 1,
          fontSize: "1.2rem",
          borderRadius: 2,
          backgroundColor: "#3f51b5",
          "&:hover": {
            backgroundColor: "#2e3b8a",
          },
        }}
      >
        Login
      </Button>
    </Box>
  );
};

export default Homepage;
