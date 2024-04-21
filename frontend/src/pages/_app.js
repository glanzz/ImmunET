import Header from "@/components/shared/Header";
import Sidebar from "@/components/shared/Sidebar";
import "@/styles/globals.css";
import { useState } from "react";
import "react-calendar/dist/Calendar.css";
import "../../node_modules/react-toastify/dist/ReactToastify.css";
export default function App({ Component, pageProps }) {
  const [open, setOpen] = useState(false);
  const [nav, setNav] = useState(false);

  return (
    <div className="flex bg-[#EFF2F5] ">
      {/* Sidebar (Fixed) */}
      <div className="">
        <Sidebar open={open} setOpen={setOpen} nav={nav} setNav={setNav} />
      </div>

      <div className="w-full">
        <Header open={open} setOpen={setOpen} nav={nav} setNav={setNav} />
        <div className="">
          <Component {...pageProps} />
        </div>
      </div>
    </div>
  );
}
