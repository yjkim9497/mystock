import { useState } from "react";
import { LoginInfo } from "../../types/user";
import { useMutation } from "react-query";
import { login } from "../../apis/userApi";
import {
  Box,
  Typography,
  TextField,
  Button,
  Divider,
} from "@mui/material";
import { useNavigate } from "react-router-dom";

const Login = () => {
    const navigate = useNavigate();
  const [formData, setFormData] = useState<LoginInfo>({
    email: "",
    password: "",
  });

  const mutation = useMutation({
    mutationFn: login,
    onSuccess: () => {
        navigate("/");
    }
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    mutation.mutate(formData);
  };

  const handleKakaoLogin = () => {
    console.log("카카오 로그인");
  };

  const handleSignupRedirect = () => {
    navigate("/signup"); // Navigate to /signup page
  };

  return (
    <Box
      display="flex"
      alignItems="center"
      justifyContent="center"
      minHeight="100vh"
      bgcolor="background.default"
      p={3}
    >
      <Box
        p={4}
        boxShadow={3}
        borderRadius={2}
        maxWidth={400}
        width="100%"
        sx={{
          backgroundColor: "background.paper",
        }}
      >
        <Typography variant="h4" component="h1" align="center" gutterBottom>
          Login
        </Typography>
        
        <form onSubmit={handleSubmit}>
          <TextField
            label="ID"
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            fullWidth
            required
            variant="outlined"
            margin="normal"
            placeholder="ID"
            InputProps={{
              sx: {
                backgroundColor: "transparent",
                borderRadius: 1,
              },
            }}
          />
          
          <TextField
            label="Password"
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            fullWidth
            required
            variant="outlined"
            margin="normal"
            placeholder="PASSWORD"
            InputProps={{
              sx: {
                backgroundColor: "transparent",
                borderRadius: 1,
              },
            }}
          />
          
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            sx={{ mt: 2, py: 1.5, fontSize: "1rem", borderRadius: 2, }}
          >
            Login
          </Button>
        </form>

        <Divider sx={{ my: 3 }} variant="middle">
          <Typography color="textSecondary">또는</Typography>
        </Divider>

        <Button
          onClick={handleKakaoLogin}
          fullWidth
          sx={{
            backgroundColor: "#FEE500",
            color: "black",
            border: "1px solid #FEE500",
            borderRadius: 2,
            "&:hover": { backgroundColor: "#FFD400" },
            py: 1.5,
            fontSize: "1rem",
          }}
        >
          Login with Kakao
        </Button>

        <Box textAlign="center" mt={2}>
          <Typography
            variant="body2"
            color="textSecondary"
            onClick={handleSignupRedirect}
            sx={{ cursor: "pointer", "&:hover": { textDecoration: "underline" } }}
          >
            Signup
          </Typography>
        </Box>
      </Box>
    </Box>
  );
};

export default Login;
