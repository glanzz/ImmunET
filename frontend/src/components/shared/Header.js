import Image from "next/image";
import { RiMenu4Fill } from "react-icons/ri";
import profile from "../../../public/assets/profile.jpg";
const Header = ({ open, setOpen, nav, setNav }) => {
  const handleClick = () => {
    setOpen(!open);
    setNav(!nav);
  };
  return (
    <div className=" w-full py-5 bg-white sticky top-0 z-10">
      <div className="flex justify-between">
        <div className="flex items-center w-2/4">
          <div className="px-4 flex">
            <button onClick={handleClick}>
              <RiMenu4Fill className="text-xl " />
            </button>
          </div>
          {/* search bar */}
          <div className="w-full flex justify-center">
            <div className="flex items-center relative "></div>
          </div>
        </div>
        {/* profile details */}
        <div className="flex items-center justify-end space-x-7">
          <button className="hover:bg-secondary"></button>
          <button className="flex items-center space-x-2 hover:bg-secondary px-4">
            <Image src={profile} alt="" className="w-14 h-14 rounded-full" />
            <h1 className="hidden sm:block">Dr. Peters</h1>
          </button>
        </div>
      </div>
    </div>
  );
};

export default Header;
