import { createContext, useState, useMemo } from "react";
import { createTheme } from "@mui/material/styles";

// color design tokens export
export const tokens = (mode) => ({
  ...(mode === "dark"
    ? {
        grey: {
          100: "#c1d6bb",
          200: "#c1d6bb",
          300: "#c1d6bb",
          400: "#c1d6bb",
          500: "#c1d6bb",
          600: "#c1d6bb",
          700: "#c1d6bb",
          800: "#c1d6bb",
          900: "#c1d6bb",
        },
        primary: {
          100: "#6cd454",
          200: "#aae49d",
          300: "#345424",
          400: "#345424",
          500: "#143424",
          600: "#101624",
          700: "#0c101b",
          800: "#080b12",
          900: "#040509",
        },
        greenAccent: {
          100: "#345424",
          200: "#345424",
          300: "#34542c",
          400: "#6cd454",
          500: "#6cd454",
          600: "#3da58a",
          700: "#6cd454",
          800: "#aae49d",
          900: "#aae49d",
        },
        redAccent: {
          100: "#f8dcdb",
          200: "#f1b9b7",
          300: "#e99592",
          400: "#e2726e",
          500: "#db4f4a",
          600: "#af3f3b",
          700: "#832f2c",
          800: "#58201e",
          900: "#2c100f",
        },
        blueAccent: {
          100: "red",
          200: "red",
          300: "#a4a9fc",
          400: "black",
          500: "#a2834f",
          600: "#535ac8",
          700: "#a2834f",
          800: "red",
          900: "red",
        },
      }
    : {
      grey: {
        100: "#345424",
        200: "#345424",
        300: "#345424",
        400: "#345424",
        500: "#345424",
        600: "#345424",
        700: "#345424",
        800: "#345424",
        900: "#345424",
        },
        primary: {
          100: "#aae49d",
          200: "#345424",
          300: "#345424",
          400: "#c4f0b7",
          500: "#345424",
          600: "#101624",
          700: "#0c101b",
          800: "#345424",
          900: "#345424",
        },
        greenAccent: {
          100: "#345424",
          200: "#345424",
          300: "#34542c",
          400: "#345424",
          500: "#34542c",
          600: "#34542c",
          700: "#6cd454",
          800: "#aae49d",
          900: "#aae49d",
        },
        redAccent: {
          100: "#f8dcdb",
          200: "#f1b9b7",
          300: "#e99592",
          400: "#e2726e",
          500: "#db4f4a",
          600: "#af3f3b",
          700: "#832f2c",
          800: "#58201e",
          900: "#2c100f",
        },
        blueAccent: {
          100: "#34542c",
          200: "#34542c",
          300: "#a4a9fc",
          400: "#34542c",
          500: "#6cd454",
          600: "#535ac8",
          700: "#d4b68a",
          800: "red",
          900: "#c1d6bb",
        },
      }),
});

// mui theme settings
export const themeSettings = (mode) => {
  const colors = tokens(mode);
  return {
    palette: {
      mode: mode,
      ...(mode === "dark"
        ? {
            // palette values for dark mode
            primary: {
              main: colors.primary[500],
            },
            secondary: {
              main: colors.greenAccent[500],
            },
            neutral: {
              // light: colors.grey[700],
              main: colors.grey[500],
              light: colors.grey[100],
            },
            background: {
              default: colors.primary[500],
            },
          }
        : {
            // palette values for light mode
            primary: {
              main: colors.primary[100],
            },
            secondary: {
              main: colors.greenAccent[500],
            },
            neutral: {
              // light: colors.grey[700],
              main: colors.grey[500],
              light: colors.grey[100],
            },
            background: {
              default: "#fcfcfc",
            },
          }),
    },
    typography: {
      fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
      fontSize: 12,
      h1: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 40,
      },
      h2: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 32,
      },
      h3: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 24,
      },
      h4: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 20,
      },
      h5: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 16,
      },
      h6: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 14,
      },
    },
  };
};

// context for color mode
export const ColorModeContext = createContext({
  toggleColorMode: () => {},
});

export const useMode = () => {
  const [mode, setMode] = useState("light");

  const colorMode = useMemo(
    () => ({
      toggleColorMode: () =>
        setMode((prev) => (prev === "light" ? "light" : "light")),
    }),
    []
  );

  const theme = useMemo(() => createTheme(themeSettings(mode)), [mode]);
  return [theme, colorMode];
};
