import { useState } from "react";
import { useMutation } from "react-query";
import { signup } from "../../apis/userApi";
import { SignupInfo } from "../../types/user";
import {
  Box,
  Typography,
  TextField,
  Button,
  Container,
  CircularProgress,
} from "@mui/material";
import { useNavigate } from "react-router-dom";

const Signup = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState<SignupInfo>({
        email: "",
        password: "",
        name: "",
      });
    
      const mutation = useMutation({
        mutationFn: signup,
        onSuccess: () => {
          alert("회원가입이 완료되었습니다.");
          navigate("/login")
        },
        onError: (error) => {
          console.error("Signup failed", error);
          alert("회원가입에 실패했습니다.");
        },
      });
    
      const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
      };
    
      const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        mutation.mutate(formData);
      };
  return (
    <Container
      component="main"
      maxWidth="xs"
      sx={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        minHeight: "100vh",
      }}
    >
      <Box
        sx={{
          width: "100%",
          p: 4,
          borderRadius: 2,
          boxShadow: 3,
          backgroundColor: "background.paper",
        }}
      >
        <Typography component="h1" variant="h5" align="center" mb={2}>
          Create Account
        </Typography>
        <form onSubmit={handleSubmit}>
          <TextField
            label="Email"
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            fullWidth
            required
            variant="outlined"
            margin="normal"
            placeholder="이메일을 입력하세요"
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
            placeholder="비밀번호를 입력하세요"
          />
          <TextField
            label="Name"
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            fullWidth
            required
            variant="outlined"
            margin="normal"
            placeholder="이름을 입력하세요"
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            sx={{ mt: 3, py: 1.5 }}
            disabled={mutation.isLoading}
          >
            {mutation.isLoading ? (
              <CircularProgress size={24} sx={{ color: "white" }} />
            ) : (
              "회원가입"
            )}
          </Button>
        </form>
      </Box>
    </Container>
  )
}

export default Signup