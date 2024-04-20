/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      colors: {
        custom_button_color: "var(--custom_button_color)",
        custom_button_hover_color: "var(--custom_button_hover_color)",
        bold_text_colour: "var(--bold-text-colour)",
        secondary_colour: "var(--secondary-colour)",
        primary_colour: "var(--primary-colour)",
        third_colour: "var(--third-colour)",
        fourth_blue_colour: "var(--fourth-blue-colour)",
        fifth_green_colour: "var(--fifth-green-colour)",
      },
      backgroundColor: {
        primary_background: "#ADBC9F",
        secondary_background: "#c5f3cb",
      },
      textColor: {
        primary_text: "#424e79",
        secondary_pink_text: "#FF8A8A",
        third_green_text: "#45B854",
        orangetxt: "#FFA958",
        lightPinktxt: "#FF8A8A",
        darkBluetxt: "#3A4C70",
        darkGraytxt: "#9BA7BF",
      },
      fontFamily: {
        body: ["Barlow"],
      },
    },
  },
  plugins: [],
};
